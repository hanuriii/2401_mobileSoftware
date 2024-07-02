package ddwu.com.mobile.finalreport

import java.io.Serializable

class SubjectDto(val id: Long, var mvImage: Int, var mvType: String, var mvTitle: String?, var mvDirector: String?, var mvScore: String?, var mvDate: String?) : Serializable {
}