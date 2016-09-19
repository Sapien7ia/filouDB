package filou.media;

import filou.util.Entry;
import filou.util.Loadable;
import filou.util.Storable;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javafx.util.Pair;

/**
 *
 * @author dark
 */
public final class REG {

  public static <T> void set(Register register, String key, Class<T> type, T value) {
    register.byClass.get(type).set(key, value);
  }

  public static <T> void set(Register register, String key, String fileSuffix, Class<T> type, T value) {
    register.byClass.get(type).set(key, fileSuffix, value);
  }

  public static <T> boolean contains(Register register, String key, Class<T> type) {
    return register.byClass.get(type).contains(key);
  }

  public static <T> T get(Register register, String key, Class<T> type) {
    return (T) register.byClass.get(type).get(key);
  }

  /**
   *
   * @param <T>
   * @param register
   * @param key
   * @param type
   * @param defaultValue
   * @return
   */
  public static <T> T init(Register register, String key, Class<T> type, T defaultValue) {
    if (contains(register, key, type)) {
      return get(register, key, type);
    } else {
      set(register, key, type, defaultValue);
      return defaultValue;
    }
  }

  /**
   *
   * @param <T>
   * @param register
   * @param key
   * @param fileSuffix
   * @param type
   * @param defaultValue
   * @return
   */
  public static <T> T init(Register register, String key, String fileSuffix, Class<T> type, T defaultValue) {
    if (contains(register, key, type)) {
      return get(register, key, type);
    } else {
      set(register, key, fileSuffix, type, defaultValue);
      return defaultValue;
    }
  }

  public static <T> T get(Register register, String key, Class<T> type, T defaultValue) {
    return (T) register.byClass.get(type).get(key, defaultValue);
  }

  public static <T> Stream<T> stream(Register register, Class<T> type) {
    return register.byClass.get(type).stream();
  }

  public static <T> void forEach(Register register, Class<T> type, Consumer<Pair<String, T>> consumer) {
    register.byClass.get(type).stream().forEach(consumer);
  }

  public static <T extends filou.util.Entry> void setEntry(Register register, String key, T value) {
    register.byClass.get(filou.util.Entry.class).set(key, value);
  }

  public static boolean containsEntry(Register register, String key) {
    return register.byClass.get(filou.util.Entry.class).contains(key);
  }

  public static <T extends filou.util.Entry> T getEntry(Register register, String key) {
    return (T) register.byClass.get(filou.util.Entry.class).get(key);
  }

  public static <T extends filou.util.Entry> T getEntry(Register register, String key, T defaultValue) {
    return (T) register.byClass.get(filou.util.Entry.class).get(key, defaultValue);
  }

  public static Stream<Pair<String, Entry>> entryStream(Register register) {
    return register.stream(filou.util.Entry.class);
  }

  public static void forEachEntry(Register register, Consumer<Pair<String, Entry>> consumer) {
    register.byClass.get(filou.util.Entry.class).stream().forEach(consumer);
  }

  public static void set(Register register, String key, Storable storable) {
    REG.setEntry(register, key, storable.save(register));
  }

  public static void get(Register register, String key, Loadable loadable) {
    if (REG.containsEntry(register, key)) {
      loadable.load(register, REG.getEntry(register, key));
    }
  }

  private REG() throws IllegalAccessException {
    throw new IllegalAccessException();
  }

}
