package top.riverelder.android.bilibilifucker;

import java.util.Arrays;

public class MyByteBuffer {

    private byte[] data;
    private int pointer = 0;

    public MyByteBuffer(int initialCapacity) {
        data = new byte[initialCapacity];
    }

    private void require(int length) {
        if (data.length - pointer < length) {
            byte[] oldData = data;
            data = new byte[2 * oldData.length];
            System.arraycopy(oldData, 0, data, 0, oldData.length);
        }
    }

    public void add(byte[] data, int offset, int length) {
        require(length);
        System.arraycopy(data, offset, this.data, pointer, length);
    }

    public byte[] copyData() {
        return Arrays.copyOfRange(data, 0, pointer);
    }
}
