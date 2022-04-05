import sun.misc.Unsafe;
import java.lang.reflect.Field;

class Data {
	public int field1;
	public int field2;
	public int field3;

	final static long field3Offset = UnsafeTest.unsafe.objectFieldOffset(UnsafeTest.dataField);

	static void run() throws Exception {
		System.out.println("field3Offset (computed at build time): " + field3Offset);
		System.out.println("field3 offset (computed at run time): " + UnsafeTest.unsafe.objectFieldOffset(Data.class.getField("field3")));
	}
}

public class UnsafeTest {
	public static Field dataField;
	public static Unsafe unsafe;

	static {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
			dataField = Data.class.getField("field3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void main(String args[]) throws Exception {
		Data.run();
	}
}
