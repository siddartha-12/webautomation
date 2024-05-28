package powerautomate.codeless.registeries;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class SimpleDateFormatRegistry implements Registry<String, SimpleDateFormat> {
    private final Map<String,SimpleDateFormat> simpleDateFormatMap;
    private static SimpleDateFormatRegistry SIMPLE_DATE_FORMAT_REGISTRY = null;
    private SimpleDateFormatRegistry(){
        simpleDateFormatMap = new HashMap<>();
    }
    @Override
    public SimpleDateFormat get(String key) {
        return simpleDateFormatMap.getOrDefault(key,null);
    }

    @Override
    public void add(String key, SimpleDateFormat simpleDateFormat) {
        simpleDateFormatMap.put(key,simpleDateFormat);
    }

    @Override
    public void remove(String key) {
        if(simpleDateFormatMap.containsKey(key)){
            simpleDateFormatMap.remove(key);
        }
    }

    public static SimpleDateFormatRegistry getInstance(){
        if (SIMPLE_DATE_FORMAT_REGISTRY == null){
            synchronized (SimpleDateFormatRegistry.class){
                if (SIMPLE_DATE_FORMAT_REGISTRY==null){
                    SIMPLE_DATE_FORMAT_REGISTRY = new SimpleDateFormatRegistry();
                }
            }
        }
        return SIMPLE_DATE_FORMAT_REGISTRY;
    }


}
