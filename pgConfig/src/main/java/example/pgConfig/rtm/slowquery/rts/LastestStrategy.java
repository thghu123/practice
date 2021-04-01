//package example.pgConfig.rtm.slowquery.rts;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class LastestStrategy<T extends Message> implements CollectStrategy<T> {
//
//    private final ConcurrentHashMap<Long, List<T>> collectMap = new ConcurrentHashMap<>();
//
//    @Override
//    public void update(long instanceId, LocalDateTime time, List<T> messages) {
//        collectMap.put(instanceId, messages);
//    }
//
//    @Override
//    public List<T> current(long instanceId) {
//        return collectMap.get(instanceId);
//    }
//}
