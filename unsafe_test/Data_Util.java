import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.RecomputeFieldValue.Kind;
import com.oracle.svm.core.annotate.TargetClass;
import com.oracle.svm.core.annotate.Alias;

@TargetClass(Data.class)
public final class Data_Util {
	@Alias @RecomputeFieldValue(kind = Kind.TranslateFieldOffset)
	static long field3Offset;
}
