package src.java.main;

public interface HashTable {
    void insert(int key, Object value);
    Object search(int key);
    void remove (int key);
    int size();
    int[] keys();

    static HashTable getInstance(){
        return new HashTableImpl() {
        };
    }
}

class HashTableImpl implements HashTable {
    private static final int INITIAL_CAPACITY = 16;
    private static final int MAX_CAPACITY = 1000;
    private static final int MIN_CAPACITY = 2;
    private static final double SHRINK_RATIO = 0.25;

    private Entry[] table;
    private int size;
    private boolean flag;

    static class Entry {
        int key;
        Object value;

        Entry(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTableImpl() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
        flag = true;
    }

    @Override
    public void insert(int key, Object value) {
        if (!isUniqueKey(key)) {
            replaceValue(key, value);
            return;
        }

        if (size == MAX_CAPACITY) {
            throw new IllegalStateException("Maximum capacity of table is reached");
        }

        if (size == table.length) {
            resize(table.length * 2);
        }
        table[findSlot(key)] = new Entry(key, value);

        if (flag) {
            size++;
        }
    }

    @Override
    public Object search(int key) {
        int index = hashFunction(key);
        int initialIndex = index;

        do {
            if (table[index] != null && table[index].key == key) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
        } while (index != initialIndex);

        return null;
    }

    @Override
    public void remove(int key) {
        int index = hashFunction(key);
        int initialIndex = index;

        do {
            if (table[index] != null && table[index].key == key) {
                table[index] = null;
                if (flag) {
                    size--;
                }
            }
            index = (index + 1) % table.length;
        } while (index != initialIndex);

        double ratio = (double) size / table.length;

        if ((((ratio - SHRINK_RATIO) < 1E-7) && size != 0) && table.length != MIN_CAPACITY) {
            resize(table.length / 2);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int[] keys() {
        int[] keysArray = new int[table.length];

        for (int i = 0; i < keysArray.length; i++) {
            if (table[i] == null) {
                keysArray[i] = 0;
                continue;
            }
            keysArray[i] = table[i].key;
        }

        return keysArray;
    }

    private int findSlot(int key) {
        int index = hashFunction(key);
        int initialIndex = index;

        do {
            if (table[index] == null) {
                break;
            }
            index = (index + 1) % table.length;
        } while (index != initialIndex);

        return index;
    }

    private void resize(int newCapacity) {
        Entry[] copyTable = table.clone();
        flag = false;
        table = new Entry[newCapacity];

        for (Entry entry : copyTable) {
            if (entry != null) {
                insert(entry.key, entry.value);
            }
        }
        flag = true;
    }

    private int hashFunction(int key) {
        return Math.abs(key) % table.length;
    }

    private boolean isUniqueKey(int key) {
        int index = hashFunction(key);
        int initialIndex = index;

        while (table[index] != null) {
            if (table[index].key == key) {
                return false;
            }
            index = (index + 1) % table.length;

            if (index == initialIndex) {
                break;
            }
        }
        return true;
    }

    private void replaceValue(int key, Object value) {
        int index = hashFunction(key);
        int initialIndex = index;

        do {
            if (table[index] != null && table[index].key == key) {
                table[index].value = value;
            }
            index = (index + 1) % table.length;
        } while (index != initialIndex);
    }
}