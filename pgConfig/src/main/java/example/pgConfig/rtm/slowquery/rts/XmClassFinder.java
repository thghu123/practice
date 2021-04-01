//package example.pgConfig.rtm.slowquery.rts;
//
//import org.reflections.Reflections;
//import java.lang.annotation.Annotation;
//import java.util.Set;
//import org.reflections.scanners.SubTypesScanner;
//import org.reflections.scanners.TypeAnnotationsScanner;
//import org.reflections.util.ClasspathHelper;
//
//public class XmClassFinder {
//    public static <T extends Annotation> Set<Class<?>> findAnnotatedClasses(Class<?> baseClass, Class<T> annotationClass) {
//        return getReflections(baseClass).getTypesAnnotatedWith(annotationClass);
//    }
//
//    public static <T extends Annotation> Set<Class<?>> findAnnotatedClasses(String basePackage, Class<T> annotationClass) {
//        return getReflections(basePackage).getTypesAnnotatedWith(annotationClass);
//    }
//
//    public static <T> Set<Class<? extends T>> findSubClasses(Class<?> baseClass, Class<T> superClass) {
//        return getReflections(baseClass).getSubTypesOf(superClass);
//    }
//
//    public static <T> Set<Class<? extends T>> findSubClasses(String basePackage, Class<T> superClass) {
//        return getReflections(basePackage).getSubTypesOf(superClass);
//    }
//
//    private static Reflections getReflections(Class<?> baseClass) {
//        return new Reflections(new Object[]{ClasspathHelper.forClass(baseClass, new ClassLoader[0]), new SubTypesScanner(), new TypeAnnotationsScanner()});
//    }
//
//    private static Reflections getReflections(String basePackage) {
//        return new Reflections(new Object[]{ClasspathHelper.forPackage(basePackage, new ClassLoader[0]), new SubTypesScanner(), new TypeAnnotationsScanner()});
//    }
//
//    private XmClassFinder() {
//    }
//}
