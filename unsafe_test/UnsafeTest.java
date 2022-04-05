import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class UnsafeTest {
	public int field1;
	public int field2;
	public int field3;

	static long field3Offset;
	static Unsafe unsafe;

	static {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
			field3Offset = unsafe.objectFieldOffset(UnsafeTest.class.getField("field3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void main(String args[]) throws Exception {
		System.out.println("field3Offset (computed at build time): " + field3Offset);
		System.out.println("field3 offset (computed at run time): " + unsafe.objectFieldOffset(UnsafeTest.class.getField("field3")));
	}
}
