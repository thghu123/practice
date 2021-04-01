//package example.pgConfig.rtm.slowquery.rts;
//
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//public class MessageClasses {
//    private static final ConcurrentMap<String, Class<? extends Message>> CACHE = new ConcurrentHashMap();
//
//    public static void loadClasses(String... basePackages) {
//        String[] var1 = basePackages;
//        int var2 = basePackages.length;
//
//        for(int var3 = 0; var3 < var2; ++var3) {
//            String basePackage = var1[var3];
//            loadPackage(basePackage);
//        }
//
//    }
//
//    public static String toMessageTypeName(Class<?> messageType) {
//        return messageType.getSimpleName().toLowerCase();
//    }
//
//    public static Class<? extends Message> findClass(String messageType) {
//        return (Class)CACHE.get(messageType);
//    }
//
//    private static void loadPackage(String basePackage) {
//        Set<Class<? extends Message>> classes = XmClassFinder.findSubClasses(basePackage, Message.class);
//        Iterator var2 = classes.iterator();
//
//        while(var2.hasNext()) {
//            Class<? extends Message> cls = (Class)var2.next();
//            CACHE.put(toMessageTypeName(cls), cls);
//        }
//
//    }
//
//    private MessageClasses() {
//    }
//}
