//package example.pgConfig.rtm.slowquery.rts;
//
//import io.netty.buffer.ByteBuf;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//
//public class MessageTag {
//    private String type;
//    private long senderId;
//    private LocalDateTime sendTime;
//
//    private MessageTag(String type, long senderId, LocalDateTime sendTime) {
//        this.type = type;
//        this.senderId = senderId;
//        this.sendTime = sendTime;
//    }
//
//    public static MessageTag read(XmReadBuf readBuf) throws Exception {
//        int typeSize = readBuf.readInt();
//        String type = readBuf.readString(typeSize);
//        long senderId = readBuf.readLong();
//        long sendTime = readBuf.readLong();
//        return builder().type(type).senderId(senderId).sendTime(XmTimeUtils.toLocalDateTime(sendTime)).build();
//    }
//
//    public void write(ByteBuf byteBuf, String charSet) throws UnsupportedEncodingException {
//        byteBuf.writeInt(this.type.length());
//        byteBuf.writeBytes(this.type.getBytes(charSet));
//        byteBuf.writeLong(this.senderId);
//        byteBuf.writeLong(XmTimeUtils.toEpochMillis(this.sendTime));
//    }
//
//    public static MessageTag.MessageTagBuilder builder() {
//        return new MessageTag.MessageTagBuilder();
//    }
//
//    protected MessageTag() {
//    }
//
//    public String getType() {
//        return this.type;
//    }
//
//    public long getSenderId() {
//        return this.senderId;
//    }
//
//    public LocalDateTime getSendTime() {
//        return this.sendTime;
//    }
//
//    public static class MessageTagBuilder {
//        private String type;
//        private long senderId;
//        private LocalDateTime sendTime;
//
//        MessageTagBuilder() {
//        }
//
//        public MessageTag.MessageTagBuilder type(final String type) {
//            this.type = type;
//            return this;
//        }
//
//        public MessageTag.MessageTagBuilder senderId(final long senderId) {
//            this.senderId = senderId;
//            return this;
//        }
//
//        public MessageTag.MessageTagBuilder sendTime(final LocalDateTime sendTime) {
//            this.sendTime = sendTime;
//            return this;
//        }
//
//        public MessageTag build() {
//            return new MessageTag(this.type, this.senderId, this.sendTime);
//        }
//
//        public String toString() {
//            return "MessageTag.MessageTagBuilder(type=" + this.type + ", senderId=" + this.senderId + ", sendTime=" + this.sendTime + ")";
//        }
//    }
//}
