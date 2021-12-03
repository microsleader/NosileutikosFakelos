package micros_leader.george.nosileutikosfakelos;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public  class Server {


    public static class Crypt {

        static final long CRYPT_KEY = 11398339148487L;
        static final char ENCRYPTION_FIRST_CHAR = '~';

        public static String encrypt(String str) {
            if (str == null || str.isEmpty() || str.charAt(0) == ENCRYPTION_FIRST_CHAR) {
                return str;
            }
            Stream64 s = new Stream64();
            s.write(str);
            return String.valueOf(ENCRYPTION_FIRST_CHAR)
                    + s.encrypt(CRYPT_KEY, 0);
        }

        /**
         * @param str encrypted string
         * @return null when decryption failed
         */
        public static String decrypt(String str) {
            if (str == null || str.isEmpty() ) {
                return null;
            }
            else if ( str.charAt(0) != ENCRYPTION_FIRST_CHAR){
                return str;
            }
            str = str.substring(1);
            Stream64 s = new Stream64(str);
            if (!s.decrypt_from_this_pos(CRYPT_KEY)) {
                return null;
            }
            return s.readString_or_null();
        }

        //
        static class Stream64 {

            // bit write
            // --------------------------------------------------
            // '0..9' (48..57)  -> 0..9
            // 'A..Z' (65..90)  -> 10..35
            // 'a..z' (97..122) -> 36..61
            // '('    (40)      -> 62
            // ')'    (41)      -> 63
            //
            //  Compress String        dec
            //  --------------------------------------------------
            //  00 xxxx                 0     + 1..16
            //  01 xxxx                16     - 1..-16
            //  10 0000 xxxxxx         32     + 17..17+63
            //  10 0001 xxxxxx         33     - 17..-17-63
            //  10 0010 xxxxxx         34     char 0..63
            //  10 0011                35     #0
            //  10 0100                36     #9
            //  10 0101                37     #13
            //  10 0110                38     #13#10  (or -1)
            //  10 0111                39     space
            //  10 1000                40     + (char 43)
            //  10 1001                41     , (char 44)
            //  10 1010                42     - (char 45)
            //  10 1011                43     . (char 46)
            //  10 1100                44     repeat 1
            //  10 1101                45     repeat 2
            //  10 1110 xxxxxx         46     repeat 3..3+63
            //  10 1111 xxxxxx xxxxxx  47     repeat (3+64)..(3+64)+4095
            //  11 xxxx xxxxxx xxxxxx  48     direct unicode char 16 bit
            //
            // hdr   0..9   = int 0..9
            //      10..19  = int(bits=len*6), value[1+hdr-10]
            //      20..29  = neg -1..-10
            //      30..39  = neg(+1) int(bits=len*6), value[1+hdr-30]
            //      40..47  = float, len=1+(hdr-40), string:compressString[len]
            //      48      = float, len[1], string:compressString[len]
            //      49      = NULL
            //      50..57  = string, len=1+(hdr-50), string:compressString[len]
            //      58      = string, len[1], string:compressString[len]
            //      59      = string, len[5], string:compressString[len]
            //      60      = record, len[1], (ken,value)[len]
            //      61      = record, len[5], (ken,value)[len]
            //      62      = AObject, len:[1], value[len]
            //      63      = AObject, len:[5], value[len]
            public StringBuilder data = new StringBuilder();
            public int readPos;
            public int readEnd;

            public Stream64() {
            }

            public Stream64(String data) {
                beginRead(data);
            }

            public static String directExport(Object obj) {
                Stream64 s = new Stream64();
                s.write(obj);
                return s.export();
            }

            public boolean finish() {
                return this.readPos >= this.readEnd;
            }

            public void clear() {
                this.data.delete(0, this.data.length());
                this.readPos = 0;
                this.readEnd = 0;
            }

            public boolean beginRead(String data) {
                if (data != null) {
                    this.data.delete(0, this.data.length());
                    this.data.append(data);
                }
                this.readPos = 0;
                this.readEnd = this.data.length();
                return (readPos < readEnd);
            }

            public boolean beginRead(int readPos) {
                this.readPos = readPos;
                this.readEnd = this.data.length();
                return (readPos < readEnd);
            }

            public String export() {
                return data.toString();
            }

            // crypt_key = 48 bits. It is for safe operations in js
            public static long crypt_key(String name) {
                int len = name == null ? 0 : name.length();
                if (len == 0) {
                    return 0;
                }
                int key1 = len < 2 ? len * 0x01300203 * name.charAt(0)
                        : len < 4 ? len * 0x00067001 * name.charAt(1)
                        : len * 0x0000AE39 * name.charAt(2);
                for (int i = 0; i < len; i++) {
                    int c = name.charAt(i);
                    key1 = (31) * key1 + (c == 0 ? i : (0x10001000 / c));
                }
                int key2 = len < 3 ? len * 0x0D04811A * name.charAt(0)
                        : len < 7 ? len * 0x00014577 * name.charAt(1)
                        : len * 0x000085E9 * name.charAt(2);
                for (int i = len - 1; i >= 0; i--) {
                    int c = name.charAt(i);
                    key2 = (63 + i) * key2 + (c == 0 ? i : (0x08400000 / c));
                }
                long key = ((long) (key2 & 0x7FFFFFFF) << 17) ^ (key1 & 0x7FFFFFFF);
                return key;
            }

            public String encrypt(long crypt_key, int uncrypted_header_size) {
                // HDR uncrypted
                // crypted DATA1 [offset ... len]
                // crypted SIZE    : integer of 6 bytes length
                // crypted CHECKSUM: integer of 2 bytes length
                // crypted DATA0 [0 .. offset ]

                if (crypt_key == 0) {
                    return data.toString();
                }

                int len_origin = data.length();

                // APPEND AUX
                final int AUX_SIZE = 6 + 2;
//            int step = (len_origin <= 31) ? 1 : len_origin / 16;
//            int check_sum = 0;
//            for (int i = 0; i < len_origin; i += step) {
//                check_sum += data.charAt(i);
//            }
                int len = len_origin + AUX_SIZE;
                this._writeBytes(len, 6);
                this._writeBytes(crypt_calc_check_sum(len_origin), 2);

                // CALC OFFSET, COPY DATA TO CHARS
                int data_without_uncrypted_header = len_origin - uncrypted_header_size;
                int offset = data_without_uncrypted_header <= 0 ? 0 : (int) (crypt_key % data_without_uncrypted_header);
                int offset_plus_uncrypted_hdr = offset + uncrypted_header_size;
                char[] chars = new char[len];
                data.getChars(0, uncrypted_header_size, chars, 0);
                data.getChars(offset_plus_uncrypted_hdr, len, chars, uncrypted_header_size);
                data.getChars(uncrypted_header_size, offset_plus_uncrypted_hdr, chars, len - offset);
                // e.g.
                // -----------------------------------
                //   0.. 11 : HDR UNCRYPTED
                //  12..139 : DATA
                // 140..145 : SIZE
                // 146..147 : CHECK_SUM
                //
                // will become (e.g. offset = 88 -> offset_plus_uncrypted_hdr=100)
                //
                //   0.. 11 : HDR UNCRYPTED [0..11]
                //  12.. 51 : DATA1 [100..139]       [offset_plus_uncrypted_hdr..len_origin-1]
                //  52.. 57 : SIZE
                //  58.. 59 : CHECK_SUM
                //  60..147 : DATA0 [12..99]         [uncrypted_hdr..offset_plus_uncrypted_hdr-1]

                // CRYPT DATA BY crypt_key
                crypt_xor(crypt_key, chars, uncrypted_header_size, len);

                // RESTORE DATA (REMOVE AUX)
                this.data.setLength(len_origin);

                return new String(chars);
            }

            public boolean decrypt_from_this_pos(long crypt_key) {
                if (crypt_key == 0) {
                    return true;
                }
                final int AUX_SIZE = 6 + 2;

                int uncrypted_header_size = this.readPos;
                int len = this.readEnd;
                int len_origin = len - AUX_SIZE;
                if (len_origin < 0) {
                    return false;
                }
                int data_without_uncrypted_header = len_origin - uncrypted_header_size;
                int offset = data_without_uncrypted_header <= 0 ? 0 : (int) (crypt_key % data_without_uncrypted_header);
                int offset_plus_uncrypted_hdr = offset + uncrypted_header_size;

                char[] src = new char[len];
                data.getChars(0, len, src, 0);
                crypt_xor(crypt_key, src, uncrypted_header_size, len);

                char[] chars = new char[len];
//            data.getChars(0, uncrypted_header_size, chars, 0);
//            data.getChars(len - offset, len, chars, uncrypted_header_size);
//            data.getChars(uncrypted_header_size, len - offset, chars, offset_plus_uncrypted_hdr);
                System.arraycopy(src, 0, chars, 0, uncrypted_header_size);
                System.arraycopy(src, len - offset, chars, uncrypted_header_size, offset);
                System.arraycopy(src, uncrypted_header_size, chars, offset_plus_uncrypted_hdr, len - offset_plus_uncrypted_hdr);

//            this.data.delete(0, this.data.length());
                StringBuilder data_old = this.data;
                this.data = new StringBuilder();
                this.data.append(chars);

                this.readPos = len_origin;
                int check_size = (int) _readBytes(6);
                if (check_size != len) {
                    this.data = data_old;
                    this.readPos = uncrypted_header_size;
                    this.readEnd = len;
                    return false;
                }
                int check_sum = (int) _readBytes(2);
                if (check_sum != crypt_calc_check_sum(len_origin)) {
                    this.data = data_old;
                    this.readPos = uncrypted_header_size;
                    this.readEnd = len;
                    return false;
                }
                this.readPos = uncrypted_header_size;
                this.readEnd = len_origin;
                return true;
            }

            public int getMark() {
                return data.length();
            }

            public int transferBack(int src, int dest) {
                // moves block of [src..end] to position dest (and deleted from src position)
                int len = data.length();
                int count = len - src;
                if (count > 0) {
                    data.insert(dest, data.substring(src, len));
                    data.setLength(len);
                }
                return count;
            }

            private void _writeByte(int value) {
                this.data.append((char) (((value < 10) ? 48 : (value < 36) ? 55 : (value < 62) ? 61 : -22) + value));
            }

            private void _writeByte(StringBuilder data, int value) {
                data.append((char) (((value < 10) ? 48 : (value < 36) ? 55 : (value < 62) ? 61 : -22) + value));
            }

            private void _writeBytes(long value, int len) {
                for (int i = 0; i < len; i++) {
                    this._writeByte((int) (value) & 63);
                    value = value >> 6;
                }
            }

            private void _writeInteger(long value, int hdr) {
                if (value < 10) {
                    this._writeByte(hdr + (int) value);
                } else {
                    long limit = 64;
                    int len = 1;
                    if (value >= 0x40000000) {
                        limit = 0x1000000000L;
                        len = 6;
                    }
                    do {
                        if (value < limit) {
                            break;
                        }
                        limit = limit << 6; //* 64;
                    } while ((++len) < 10);
                    this._writeByte(hdr + 9 + len);
                    this._writeBytes(value, len);
                }
            }

            private void _writeAnyInteger(long value) {
                if (value >= 0) {
                    _writeInteger(value, 0);
                } else {
                    _writeInteger(-(value + 1), 20);
                }
            }

            private void _writeAnyDouble(double value) {
                if (value == 0) {
                    _writeByte(0);
                } else {
                    String floatStr = String.valueOf(value);
                    if (floatStr.length() > 63) {
                        floatStr = "0";
                    }
                    _writeString(floatStr, 40);
                }
            }

            private void _writeString_Repeat(StringBuilder data, int rep) {
                do {
                    if (rep == 1) {
                        _writeByte(data, 44);
                        return;
                    } else if (rep == 2) {
                        _writeByte(data, 45);
                        return;
                    } else if (rep <= 3 + 63) {
                        _writeByte(data, 46);
                        _writeByte(data, rep - 3);
                        return;
                    } else if (rep <= ((3 + 64) + 4095)) {
                        rep = rep - (3 + 64);
                        _writeByte(data, 47);
                        _writeByte(data, rep & 63);
                        _writeByte(data, (rep >> 6) & 63);
                        return;
                    } else {
                        _writeByte(data, 47);
                        _writeByte(data, 63);
                        _writeByte(data, 63);
                        rep = rep - ((3 + 64) + 4095);
                    }
                } while (true);
            }

            private void _writeString(String value, int hdr) {
                StringBuilder cc = new StringBuilder();
                int last = 0;
                int rep = 0;
                for (int i = 0, len = value.length(), high = len - 1; i < len; i++) {
                    int c = value.charAt(i);
                    if (c == 13 && i < high && value.charAt(i + 1) == 10) {
                        c = -1;
                        i++;
                    }
                    if (c == last) {
                        rep++;
                    } else {
                        if (rep > 0) {
                            _writeString_Repeat(cc, rep);
                            rep = 0;
                        }
                        int diff = c - last;
                        last = c;
                        if (diff <= 16 && diff >= -16) {
                            if (diff >= 0) {
                                _writeByte(cc, diff - 1);
                            } else {
                                _writeByte(cc, 15 - diff);
                            }
                            continue;
                        }
                        if (c <= 63) {
                            if (c == 0) {
                                _writeByte(cc, 35);
                                continue;
                            } else if (c == 9) {
                                _writeByte(cc, 36);
                                continue;
                            } else if (c == 13) {
                                _writeByte(cc, 37);
                                continue;
                            } else if (c == -1) {
                                _writeByte(cc, 38);
                                continue;
                            } else if (c == 32) {
                                _writeByte(cc, 39);
                                continue;
                            } else if (c == 43) {
                                _writeByte(cc, 40);
                                continue;
                            } else if (c == 44) {
                                _writeByte(cc, 41);
                                continue;
                            } else if (c == 45) {
                                _writeByte(cc, 42);
                                continue;
                            } else if (c == 46) {
                                _writeByte(cc, 43);
                                continue;
                            }
                            _writeByte(cc, 34);
                            _writeByte(cc, c);
                            continue;
                        }
                        if (diff <= 80 && diff >= -80) {
                            if (diff >= 0) {
                                _writeByte(cc, 32);
                                _writeByte(cc, diff - 17);
                            } else {
                                _writeByte(cc, 33);
                                _writeByte(cc, (-diff) - 17);
                            }
                            continue;
                        }
                        _writeByte(cc, 48 + (c & 15));
                        c = c >> 4;
                        _writeByte(cc, c & 63);
                        c = c >> 6;
                        _writeByte(cc, c & 63);
                    }
                }
                if (rep > 0) {
                    _writeString_Repeat(cc, rep);
                }
                int len = cc.length();
                if (len <= 7) {
                    this._writeByte(hdr + len);
                } else if (len <= 63) {
                    this._writeByte(hdr + 8);
                    this._writeByte(len);
                } else {
                    this._writeByte(hdr + 9);
                    this._writeBytes(len, 5);
                }
                this.data.append(cc);
            }

            public int writeRecord(int len) { // follow (write(key), write(value))[len]
                if (len > 0x3FFFFFFF) {
                    len = 0x3FFFFFFF;
                }
                if (len < 64) {
                    this._writeByte(60);
                    this._writeByte(len);
                } else {
                    this._writeByte(61);
                    this._writeBytes(len, 5);
                }
                return len;
            }

            public int writeArray(int len) { // follow (write(value))[len]
                if (len > 0x3FFFFFFF) {
                    len = 0x3FFFFFFF;
                }
                if (len < 64) {
                    _writeByte(62);
                    _writeByte(len);
                } else {
                    _writeByte(63);
                    _writeBytes(len, 5);
                }
                return len;
            }

            public void write(Object value) {
                if (value == null) {
                    _writeByte(49);
                } else {
                    Class cls = value.getClass();
                    if (cls == Boolean.class) {
                        _writeByte((Boolean) value ? 1 : 0);
                    } else if (cls == Byte.class) {
                        _writeAnyInteger((Byte) value);
                    } else if (cls == Short.class) {
                        _writeAnyInteger((Short) value);
                    } else if (cls == Integer.class) {
                        _writeAnyInteger((Integer) value);
                    } else if (cls == Long.class) {
                        long v = (Long) value;
                        if (v <= 0x001FFFFFFFFFFFFFL && v >= -0x001FFFFFFFFFFFFFL) {
                            _writeAnyInteger(v);
                        } else { // float
//                        _writeString(String.valueOf(v), 40);
                            _writeAnyDouble(v);
                        }
//                } else if (cls == Float.class) {
//                    _writeAnyDouble((Float) value);
//                } else if (cls == Double.class) {
//                    _writeAnyDouble((Double) value);
//                } else if (cls == int[].class) {
                    } else if (value instanceof java.lang.Number) {
                        _writeAnyDouble(((java.lang.Number) value).doubleValue());
//                } else if (C == String.class) {
                    } else if (value instanceof CharSequence) {
                        CharSequence v = (CharSequence) value;
                        if (v.length() == 0) {
                            _writeByte(0);
                        } else {
                            _writeString(v.toString(), 50);
                        }
                    } else if (cls == Character.class) {
                        _writeAnyInteger(((Character) value).charValue());
                    } else if (cls == ArrayList.class) {
                        ArrayList AL = ArrayList.class.cast(value);
                        int len = AL.size();
                        len = writeArray(len);
                        for (int i = 0; i < len; i++) {
                            write(AL.get(i));
                        }
                    } else if (cls.isArray()) {
                        int len = Array.getLength(value);
                        len = writeArray(len);
                        for (int i = 0; i < len; i++) {
                            write(Array.get(value, i));
                        }
                    } else {
                        ArrayList<Field> fields = new ArrayList<Field>();
                        Class AClass = value.getClass();
                        for (Field field : AClass.getFields()) {
                            if (!Modifier.isStatic(field.getModifiers())) {
                                String fieldName = field.getName();
                                if (fieldName.startsWith("_")) {
                                    continue;
                                }
                                fields.add(field);
                            }
                        }
                        int len = fields.size();
                        len = writeRecord(len);
                        for (int i = 0; i < len; i++) {
                            Field field = fields.get(i);
                            this.write(field.getName());
                            Object fieldValue = null;
                            try {
                                fieldValue = field.get(value);
                            } catch (Exception Ex) {
                                fieldValue = null;
                            }
                            this.write(fieldValue);
                        }
                    }
                }
                this.readEnd = this.data.length();
            }

            public void writeStream64(String stream) {
                int len = (stream == null) ? 0 : stream.length();
                write(len);
                if (len > 0) {
                    this.data.append(stream);
                }
            }

            private int _readByte(boolean goNext) {
                int p = this.readPos;
                if (p >= this.readEnd) {
                    return -1;
                }
                int c = this.data.charAt(p);
                if (goNext) {
                    this.readPos++;
                }
                if (c >= 48 && c <= 57) {
                    return c - 48;
                }
                if (c >= 65 && c <= 90) {
                    return c - 55;
                }
                if (c >= 97 && c <= 122) {
                    return c - 61;
                }
                if (c >= 40 && c <= 41) {
                    return c + 22;
                }
                this.readPos = p;
                return -1;
            }

            private long _readBytes(int len) {
                long r = 0;
                long m = 1;
                for (int i = 0; i < len; i++) {
                    int b = this._readByte(true);
                    if (b == -1) {
                        break;
                    }
                    r = r + (b * m);
                    m = m << 6;
                }
                return r;
            }

            private String _readString(int hdr) {
                StringBuilder r = new StringBuilder();
                int len;
                int b = hdr;
                if (b <= 7) {
                    len = b;
                } else if (b == 8) {
                    len = this._readByte(true);
                } else {
                    len = (int) this._readBytes(5);
                }
                int end = this.readPos + len;
                int last = 0;
                while (this.readPos < end) {
                    b = this._readByte(true);
                    int c;
                    if (b == -1) {
                        break;
                    }
                    if (b < 32) {
                        if (b <= 15) {
                            c = last + (b + 1);
                        } else {
                            c = last - (b - 15);
                        }
                    } else if (b <= 47) {
                        if (b <= 43) {
                            if (b == 32) {
                                c = last + this._readByte(true) + 17;
                            } else if (b == 33) {
                                c = last - this._readByte(true) - 17;
                            } else if (b == 34) {
                                c = this._readByte(true);
                            } else if (b == 35) {
                                c = 0;
                            } else if (b == 36) {
                                c = 9;
                            } else if (b == 37) {
                                c = 13;
                            } else if (b == 38) {
                                c = -1;
                            } else if (b == 39) {
                                c = 32;
                            } else {
                                c = b + 3;
                            }
                        } else {
                            int rpt;
                            c = last;
                            if (b == 44) {
                                rpt = 1;
                            } else if (b == 45) {
                                rpt = 2;
                            } else if (b == 46) {
                                rpt = this._readByte(true) + 3;
                            } else {
                                rpt = (int) this._readBytes(2) + (3 + 64);
                            }
                            if (c == -1) {
                                for (int i = 0; i < rpt; i++) {
                                    r.append((char) 13);
                                    r.append((char) 10);
                                }
                            } else {
                                for (int i = 0; i < rpt; i++) {
                                    r.append((char) c);
                                }
                            }
                            continue;
                        }
                    } else {
                        c = (b & 15) + ((int) this._readBytes(2) << 4);
                    }
                    if (c == -1) {
                        r.append((char) 13);
                        r.append((char) 10);
                    } else {
                        r.append((char) c);
                    }
                    last = c;
                }
                return r.toString();
            }

            public int readType() {
                // return type (-1=EOF, 0=null, 1=integer, 2=Float, 3=String, 4=record, 5=AObject
                int b = this._readByte(false);
                if (b == -1) {
                    return -1;
                }
                if (b == 49) {
                    return 0;
                }
                if (b <= 39) {
                    return 1;
                }
                if (b <= 49) {
                    return 2;
                }
                if (b <= 59) {
                    return 3;
                }
                if (b <= 61) {
                    return 4;
                }
                return 5;
            }

            public boolean checkRecord() { // (only for object or AObject) return Count;
                int b = this._readByte(false);
                return (b == 60 || b == 61);
            }

            public boolean checkArray() { // (only for object or AObject) return Count;
                int b = this._readByte(false);
                return (b == 62 || b == 63);
            }

            public int enter() { // (only for object or AObject) return Count;
                int b = this._readByte(false);
                if (b >= 60) {
                    this.readPos++;
                    if (b == 60 || b == 62) {
                        return this._readByte(true);
                    } else {
                        return (int) this._readBytes(5);
                    }
                } else {
                    this.read(null); // skip
                    return 0;
                }
            }

            public Object read() {
                return this.read(null);
            }

            public boolean readBool() {
                return readInt() != 0;
            }

            public int readInt() {
                return castInt(this.read(null));
            }

            public long readLong() {
                return castLong(this.read(null));
            }

            public double readDouble() {
                return castDouble(this.read(null));
            }

            public String readString() {
                return castString(this.read(null));
            }

            public String readString_or_null() {
                String s = readString();
                return (s == null || s.isEmpty()) ? null : s;
            }

            public String readStream64() {
                int size = readInt();
                if (size == 0) {
                    return null;
                }
                int start = this.readPos;
                this.readPos += size;
                return this.data.substring(start, this.readPos);
            }

            public byte[] readBinary() {
                return castBinary(this.read(null));
            }

            public Object[] readAObject() {
                int count = this.enter();
                if (count == 0) {
                    return null;
                }
                Object[] r = new Object[count];
                for (int i = 0; i < count; i++) {
                    r[i] = this.read();
                }
                return r;
            }

            public String[] readAString() {
                int count = this.enter();
                if (count == 0) {
                    return null;
                }
                String[] r = new String[count];
                for (int i = 0; i < count; i++) {
                    r[i] = this.readString();
                }
                return r;
            }

            public int[] readAInt() {
                int count = this.enter();
                if (count == 0) {
                    return null;
                }
                int[] r = new int[count];
                for (int i = 0; i < count; i++) {
                    r[i] = this.readInt();
                }
                return r;
            }

            public Object read(Object obj) {
                int b = this._readByte(true);
                if (b == -1) {
                    return null;
                } else if (b <= 9) {
                    return b;
                } else if (b <= 19) {
                    return this._readBytes(b - 9);
                } else if (b <= 29) {
                    return -(b - 19);
                } else if (b <= 39) {
                    return -(this._readBytes(b - 29)) - 1;
                } else if (b <= 49) {
                    if (b == 49) {
                        return null;
                    }
                    String s = this._readString(b - 40);
                    double r = 0;
                    try {
                        r = Double.parseDouble(s);
                    } catch (Exception ex) {
                        r = 0;
                    }
                    return r;
                } else if (b <= 59) {
                    return this._readString(b - 50);
                } else if (b <= 61) {
                    int len;
                    if (b == 60) {
                        len = this._readByte(true);
                    } else {
                        len = (int) this._readBytes(5);
                    }
                    if (obj == null) {
                        HashMap<Object, Object> r = new HashMap<Object, Object>();
                        for (int i = 0; i < len; i++) {
                            Object propName = this.read();
                            Object propValue = this.read();
                            r.put(propName, propValue);
                        }
                        return r;
                    } else {
                        Class c = obj.getClass();
                        for (int i = 0; i < len; i++) {
                            Object propName = this.read();
                            Field f = null;
                            if (propName != null && java.lang.String.class.isAssignableFrom(propName.getClass())) {
                                try {
                                    f = c.getField(String.class.cast(propName));
                                } catch (Exception ex) {
                                    f = null;
                                }
                            }
                            if (f != null) {
                                Class fieldType = f.getType();
                                Object existValue = null;
                                try {
                                    existValue = f.get(obj);
                                } catch (Exception ex) {
                                    existValue = null;
                                }
                                if (existValue != null && fieldType.isArray()) {
                                    this.read(existValue);
                                } else if (existValue != null && !fieldType.isPrimitive()) {
                                    this.read(existValue);
                                } else {
                                    Object propValue = this.read();
                                    try {
                                        f.set(obj, propValue);
                                    } catch (Exception ex) {
                                    }
                                }
                            } else {
                                Object propValue = this.read();
                            }
                        }
                        return obj;
                    }
                } else {
                    int len;
                    if (b == 62) {
                        len = this._readByte(true);
                    } else {
                        len = (int) this._readBytes(5);
                    }
                    Object[] r = new Object[len];
                    for (int i = 0; i < len; i++) {
                        Object propValue = this.read();
                        r[i] = propValue;
                    }
                    return r;
                }
            }

            /**
             * reads without moving readPos
             *
             * @return
             */
            public Object check() {
                int p = this.readPos;
                Object v = this.read(null);
                this.readPos = p;
                return v;
            }

            public int checkInt() {
                return castInt(check());
            }

            public boolean checkInt(int value) {
                int p = this.readPos;
                boolean r = (castInt(this.read(null)) == value);
                if (!r) {
                    this.readPos = p;
                }
                return r;
            }

            // PRIVATE
            static void crypt_xor(long crypt_key, char[] chars, int start, int end) {
                long key = crypt_key;
                for (int i = start; i < end; i++) {
                    int c = chars[i];
                    c = (c >= 48 && c <= 57) ? c - 48
                            : (c >= 65 && c <= 90) ? c - 55
                            : (c >= 97 && c <= 122) ? c - 61
                            : (c >= 40 && c <= 41) ? c + 22
                            : 0;
                    int mask = (int) key & 63;
                    c ^= mask;
                    chars[i] = (char) (((c < 10) ? 48 : (c < 36) ? 55 : (c < 62) ? 61 : -22) + c);
                    key >>= 6;
                    if (key == 0) {
                        key = crypt_key;
                    }
                }
            }

            int crypt_calc_check_sum(int len_origin) {
                int step = (len_origin <= 31) ? 1 : len_origin / 16;
                int check_sum = 0;
                for (int i = 0; i < len_origin; i += step) {
                    check_sum += data.charAt(i);
                }
                return check_sum;
            }

            static long castLong(Object value) {
                if (value == null) {
                    return 0;
                }
                Class C = value.getClass();
                if (C == Boolean.class) {
                    return ((Boolean) value) ? 1 : 0;
                } else if (C == Character.class) {
                    return (Character) value;
                } else if (value instanceof java.lang.Number) {
                    return ((java.lang.Number) value).longValue();
                } else {
                    return 0;
                }
            }

            static int castInt(Object value) {
                return (int) castLong(value);
            }

            static double castDouble(Object value) {
                if (value == null) {
                    return 0;
                }
                if (value instanceof java.lang.Number) {
                    return ((java.lang.Number) value).doubleValue();
                } else {
                    return castLong(value);
                }
            }

            static byte[] castBinary(Object value) {
                if (value == null) {
                    return null;
                }
                Class cls = value.getClass();
                if (cls == byte[].class) {
                    return (byte[]) value;
                } else {
                    return null;
                }
            }

            static String castString(Object value) {
                if (value instanceof CharSequence) {
                    return ((CharSequence) value).toString();
                } else {
                    return null;
                }
            }

        }

    }










}

