//package example.pgConfig.rtm.slowquery.rts;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Slf4j
//public abstract class Collector<T extends Message> implements Processor {
//    private final CollectStrategy<T> collectStrategy;
//    private LocalDateTime lastCollectTime;
//
//    public Collector(CollectStrategy<T> collectStrategy) {
//        this.collectStrategy = collectStrategy;
//    }
//
//    @Override
//    public void process(MessageBox messageBox) {
//        LocalDateTime collectTime = messageBox.getSendTime();
//        try {
//            checkLastCollect(collectTime);
//            collectStrategy.update(messageBox.getSenderId(), collectTime, (List<T>) messageBox.getMessages());
//        } catch (Exception e) {
//            log.debug("summary skipped, invalid collect time");
//        }
//    }
//
//    public List<T> current(long instanceId) {
//        return collectStrategy.current(instanceId);
//    }
//
//    private void checkLastCollect(LocalDateTime time) throws Exception {
//        if (lastCollectTime != null
//                && (time.isEqual(lastCollectTime) || time.isBefore(lastCollectTime))) {
//            throw new Exception();
//        }
//        lastCollectTime = time;
//    }
//}
