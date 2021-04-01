//package example.pgConfig.rtm.slowquery.rts;
//
//import io.netty.buffer.ByteBuf;
//import java.io.UnsupportedEncodingException;
//
//public class XmReadBuf {
//    private static final String CHARACTER_SET = "UTF-8";
//    private final ByteBuf byteBuf;
//    private int readerIndex = 0;
//
//    private XmReadBuf(ByteBuf byteBuf) {
//        this.byteBuf = byteBuf;
//    }
//
//    public static XmReadBuf of(ByteBuf byteBuf) {
//        return new XmReadBuf(byteBuf);
//    }
//
//    public final int getReaderIndex() {
//        return this.readerIndex;
//    }
//
//    public final void initReadIndex() {
//        this.byteBuf.readerIndex(this.byteBuf.readerIndex() - this.readerIndex);
//        this.readerIndex = 0;
//    }
//
//    public final byte readByte() throws Exception {
//        this.checkBytes(1);
//        return this.byteBuf.readByte();
//    }
//
//    public final short readUnsignedByte() throws Exception {
//        this.checkBytes(1);
//        return this.byteBuf.readUnsignedByte();
//    }
//
//    public final short readShort() throws Exception {
//        this.checkBytes(2);
//        return this.byteBuf.readShort();
//    }
//
//    public final int readUnsignedShort() throws Exception {
//        this.checkBytes(2);
//        return this.byteBuf.readUnsignedShort();
//    }
//
//    public final int readInt() throws Exception {
//        this.checkBytes(4);
//        return this.byteBuf.readInt();
//    }
//
//    public final long readUnsignedInt() throws Exception {
//        this.checkBytes(4);
//        return this.byteBuf.readUnsignedInt();
//    }
//
//    public final long readLong() throws Exception {
//        this.checkBytes(8);
//        return this.byteBuf.readLong();
//    }
//
//    public final double readDouble() throws Exception {
//        this.checkBytes(8);
//        return this.byteBuf.readDouble();
//    }
//
//    public final byte[] readBytes(int size) throws Exception {
//        this.checkBytes(size);
//        byte[] temp = new byte[size];
//        this.byteBuf.readBytes(temp);
//        return temp;
//    }
//
//    public final String readString(int size) throws Exception, UnsupportedEncodingException {
//        byte[] temp = this.readBytes(size);
//        return (new String(temp, "UTF-8")).trim();
//    }
//
//    private void checkBytes(int size) throws Exception {
//        if (this.byteBuf.readableBytes() < size) {
//            throw new Exception();
//        } else {
//            this.readerIndex += size;
//        }
//    }
//}
