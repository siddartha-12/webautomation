package powerautomate.codeless.registeries;

import java.security.Key;
import java.util.Map;

public interface Registry<Key,Value> {
    public Value get(Key key);
    public void add(Key key,Value value);
    public void remove(Key key);
}
