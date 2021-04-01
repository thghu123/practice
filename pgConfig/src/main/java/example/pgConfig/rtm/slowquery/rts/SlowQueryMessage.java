package example.pgConfig.rtm.slowquery.rts;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SlowQueryMessage implements Message {
    private long pid;
    private String queryId;
    private long elapsed;

    public static SlowQueryMessage.SlowQueryMessageBuilder builder() {
        return new SlowQueryMessage.SlowQueryMessageBuilder();
    }

    public String toString() {
        long var10000 = this.getPid();
        return "SlowQueryMessage(pid=" + var10000 + ", queryId=" + this.getQueryId() + ", elapsed=" + this.getElapsed() + ")";
    }

    public long getPid() {
        return this.pid;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public long getElapsed() {
        return this.elapsed;
    }

    protected SlowQueryMessage() {
    }

    private SlowQueryMessage(final long pid, final String queryId, final long elapsed) {
        this.pid = pid;
        this.queryId = queryId;
        this.elapsed = elapsed;
    }

    public static class SlowQueryMessageBuilder {
        private long pid;
        private String queryId;
        private long elapsed;

        SlowQueryMessageBuilder() {
        }

        public SlowQueryMessage.SlowQueryMessageBuilder pid(final long pid) {
            this.pid = pid;
            return this;
        }

        public SlowQueryMessage.SlowQueryMessageBuilder queryId(final String queryId) {
            this.queryId = queryId;
            return this;
        }

        public SlowQueryMessage.SlowQueryMessageBuilder elapsed(final long elapsed) {
            this.elapsed = elapsed;
            return this;
        }

        public SlowQueryMessage build() {
            return new SlowQueryMessage(this.pid, this.queryId, this.elapsed);
        }

        public String toString() {
            return "SlowQueryMessage.SlowQueryMessageBuilder(pid=" + this.pid + ", queryId=" + this.queryId + ", elapsed=" + this.elapsed + ")";
        }
    }
}
