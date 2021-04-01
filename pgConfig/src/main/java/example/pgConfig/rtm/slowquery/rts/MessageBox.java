//package example.pgConfig.rtm.slowquery.rts;
//
//
//import io.netty.buffer.ByteBuf;
//
//import java.io.UnsupportedEncodingException;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//public class MessageBox {
//    private MessageTag tag;
//    private byte[] data;
//
//    private MessageBox(MessageTag tag, List<Message> messages) {
//        this.tag = tag;
//        this.data = MessageParser.toBytes(messages);
//    }
//
//    private MessageBox(MessageTag tag, byte[] data) {
//        this.tag = tag;
//        this.data = data;
//    }
//
//    private MessageBox(long senderId, LocalDateTime sendTime, List<Message> messages) {
//        this.tag = MessageTag.builder().senderId(senderId).sendTime(sendTime).type(MessageClasses.toMessageTypeName(((Message)messages.get(0)).getClass())).build();
//        this.data = MessageParser.toBytes(messages);
//    }
//
//    public static MessageBox read(XmReadBuf readBuf) throws Exception {
//        MessageTag tag = MessageTag.read(readBuf);
//        int messageSize = readBuf.readInt();
//        byte[] data = readBuf.readBytes(messageSize);
//        return ofBytes().tag(tag).data(data).build();
//    }
//
//    public void write(ByteBuf byteBuf, String charSet) throws UnsupportedEncodingException {
//        this.tag.write(byteBuf, charSet);
//        byteBuf.writeInt(this.data.length);
//        byteBuf.writeBytes(this.data);
//    }
//
//    public String getType() {
//        return this.tag.getType();
//    }
//
//    public long getSenderId() {
//        return this.tag.getSenderId();
//    }
//
//    public LocalDateTime getSendTime() {
//        return this.tag.getSendTime();
//    }
//
//    public <T extends Message> List<? extends Message> getMessages() {
//        return MessageParser.toList(this.data, MessageClasses.findClass(this.tag.getType()));
//    }
//
//    public static MessageBox.of of() {
//        return new MessageBox.of();
//    }
//
//    public static MessageBox.ofBytes ofBytes() {
//        return new MessageBox.ofBytes();
//    }
//
//    public static MessageBox.MessageBoxBuilder builder() {
//        return new MessageBox.MessageBoxBuilder();
//    }
//
//    protected MessageBox() {
//    }
//
//    public static class MessageBoxBuilder {
//        private long senderId;
//        private LocalDateTime sendTime;
//        private List<Message> messages;
//
//        MessageBoxBuilder() {
//        }
//
//        public MessageBox.MessageBoxBuilder senderId(final long senderId) {
//            this.senderId = senderId;
//            return this;
//        }
//
//        public MessageBox.MessageBoxBuilder sendTime(final LocalDateTime sendTime) {
//            this.sendTime = sendTime;
//            return this;
//        }
//
//        public MessageBox.MessageBoxBuilder messages(final List<Message> messages) {
//            this.messages = messages;
//            return this;
//        }
//
//        public MessageBox build() {
//            return new MessageBox(this.senderId, this.sendTime, this.messages);
//        }
//
//        public String toString() {
//            return "MessageBox.MessageBoxBuilder(senderId=" + this.senderId + ", sendTime=" + this.sendTime + ", messages=" + this.messages + ")";
//        }
//    }
//
//    public static class ofBytes {
//        private MessageTag tag;
//        private byte[] data;
//
//        ofBytes() {
//        }
//
//        public MessageBox.ofBytes tag(final MessageTag tag) {
//            this.tag = tag;
//            return this;
//        }
//
//        public MessageBox.ofBytes data(final byte[] data) {
//            this.data = data;
//            return this;
//        }
//
//        public MessageBox build() {
//            return new MessageBox(this.tag, this.data);
//        }
//
//        public String toString() {
//            MessageTag var10000 = this.tag;
//            return "MessageBox.ofBytes(tag=" + var10000 + ", data=" + Arrays.toString(this.data) + ")";
//        }
//    }
//
//    public static class of {
//        private MessageTag tag;
//        private List<Message> messages;
//
//        of() {
//        }
//
//        public MessageBox.of tag(final MessageTag tag) {
//            this.tag = tag;
//            return this;
//        }
//
//        public MessageBox.of messages(final List<Message> messages) {
//            this.messages = messages;
//            return this;
//        }
//
//        public MessageBox build() {
//            return new MessageBox(this.tag, this.messages);
//        }
//
//        public String toString() {
//            return "MessageBox.of(tag=" + this.tag + ", messages=" + this.messages + ")";
//        }
//    }
//}
