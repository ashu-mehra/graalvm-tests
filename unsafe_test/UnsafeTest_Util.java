import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.RecomputeFieldValue.Kind;
import com.oracle.svm.core.annotate.TargetClass;
import com.oracle.svm.core.annotate.Alias;

@TargetClass(UnsafeTest.class)
public final class UnsafeTest_Util {
	/* UnsafeTest::field3Offset stores the field offset. Annotate it for recomputation.
	 * Recomputation is of type TranslateFieldOffset.
	 * (https://javadoc.io/static/org.graalvm.nativeimage/svm/19.3.0.2/com/oracle/svm/core/annotate/RecomputeFieldValue.Kind.html#TranslateFieldOffset)
	 */
	@Alias @RecomputeFieldValue(kind = Kind.TranslateFieldOffset)
	static long field3Offset;
}
