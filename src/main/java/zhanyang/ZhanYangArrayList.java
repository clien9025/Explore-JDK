package zhanyang;

import java.util.*;

public class ZhanYangArrayList<E> implements List<E> {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    protected transient int modCount;


    /*
    初始化内部数组
     */
    public ZhanYangArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (null == o) {
            for (int i = 0; i < size; i++) {
                if (null == elements[i]) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (null == c) {
            throw new NullPointerException();
        }

        for (Object obj : c) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean add(E e) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = e;
        return true;
    }

    // TODO
    @Override
    public void add(int index, E element) {
        if (null == element) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        //TODO 帮我完善下面的逻辑

        // 1. 扩容
        if (size == elements.length) {
            ensureCapacity();
        }
        // 2. 挪动数组
        if (index < size) {
            System.arraycopy(element, index, element, index + 1, size - index);
        }
        // 3. 添加元素到新数组
        elements[index] = element;
        size++;
    }

    /*
    确保数组容量
     */
    private void ensureCapacity() {
        int newSize = elements.length * 2;
        Arrays.copyOf(elements, newSize);
    }

    /*
    确保数组容量-放新元素
     */
    private void ensureExtraCapacity(int numNew) {
        int miniCapacity = size + numNew;
        int oldCapacity = elements.length;

        if (miniCapacity > elements.length) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < miniCapacity) {
                newCapacity = miniCapacity;
            }
            Arrays.copyOf(elements, newCapacity);// 扩容数组
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        // todo 帮我补充下面的逻辑代码
        if (index < 0 || index < size) {
            throw new IndexOutOfBoundsException();
        }
        // 1. 扩容
        // 需要加的元素的数量
        int numNew = c.size();
        ensureExtraCapacity(numNew);  // 确保有足够的空间来放置新元素

        // 2. 腾空间
        int numMoved = size - index - 1;// 确定从index开始要移动的元素数量
        if (numMoved > 0) {
            System.arraycopy(elements, index, elements, index + numNew, numMoved);
        }

        // 3. 插入数据
        for (E e : c) {
            elements[index++] = e;
        }
        // 更新数组长度
        size += numNew;
        return numNew != 0;// 如果添加了元素，返回true
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // todo 帮我检查下下面的代码有无逻辑错误,有无补充完善的地方,你也可以采用你自己的想法,出一版你自己的版本
        if (c == null) {
            throw new NullPointerException();
        }
        for (E listElement : c) {
            add(listElement);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        // todo 完成 List 接口的 remove 方法实现
        for (int index = 0; index < size; index++) {
            if (o == null ? elements == null : o == elements[index]) {
                fastRemove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = (E) elements[index];

        fastRemove(index);

        return oldElement;
    }

    /*
    快速移除元素,在现有数组中移动元素
     */
    private void fastRemove(int index) {
        int numRemove = size - index - 1;
        if (numRemove > 0) {
            // System.arraycopy()只是移动或复制数据，它不会创建新的数组对象
            System.arraycopy(elements, index + 1, elements, index, numRemove);
        }
        elements[--size] = null;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO 完成下面的逻辑
        if (null == c) {
            throw new NullPointerException();
        }
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(elements[i])) {
                fastRemove(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }


    @Override
    public boolean retainAll(Collection<?> c) {

        // 标记是否有改变
        boolean isModified = false;

        // 从当前ArrayList中逐一检查元素
        for (int i = 0; i < size; i++) {
            // 如果当前元素不在给定集合c中，则删除
            if (!c.contains(elements[i])) {
                fastRemove(i);
                i--;  // 调整索引，因为我们删除了一个元素
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        // TODO 完成下面的逻辑

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked")
        E element = (E) elements[index];
        return element;
    }

    @Override
    public E set(int index, E element) {
        //TODO 完成下面的逻辑,也可以不用我下面的思路
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (null == element) {
            throw new NullPointerException();
        }

        @SuppressWarnings("unchecked")
        E oldElement = (E) elements[index];

        elements[index] = element;

        return oldElement;
    }

    // TODO
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    // TODO
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    @Override
    public int indexOf(Object o) {
        // TODO 完成下面的逻辑
        if (null == o) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }

            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO 完成下面的逻辑
        if (null == o) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    @Override
    public Iterator<E> iterator() {
        // TODO 完成下面的逻辑
        return new Itr();
    }

    /*
    补充
     */
    private class Itr implements Iterator<E> {
        int cursor;       // 迭代器当前的位置
        int lastRet = -1; // 上一次返回的元素的位置

        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size; // 如果游标不等于数组大小，说明还有元素可以迭代
        }

        @Override
        public E next() {
            checkForComodification();
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            if (cursor >= elements.length) {
                throw new ConcurrentModificationException();
            }
            lastRet = cursor;
            cursor += 1;
            return (E) elements[lastRet];
        }

        final void checkForComodification() {
            if (ZhanYangArrayList.this.modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }


        // 可选：如果您还想支持在迭代时删除元素，您可以实现以下方法：
        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            try {
                ZhanYangArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }


    @Override
    public ListIterator<E> listIterator() {

        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {

        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

}


