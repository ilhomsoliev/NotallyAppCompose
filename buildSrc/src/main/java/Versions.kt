object Versions {
    const val kotlin = "1.2.21"
    const val constraint = "2.0.4"
    const val coroutines = "1.4.0"
    const val logging = "1.12.5"
    const val logback = "1.2.6"
    const val logcat = "0.1"
    const val ktlint = "0.43.2"
    val googleServices = GoogleServicesVersions
    val paging = PagingVersions
    val imageLoader = ImageLoaderVersions
    val android = AndroidVersions
    val compose = ComposeVersions
    val test = TestVersions
    val accompanist = AccompanistVersions
    val network = NetworkVersions
    val map = MapVersions
}

object MapVersions{
    const val osmdroid = "6.1.11"
    const val osmdroidMapForge = "6.1.6@aar"
    const val mapForgeMapAndroid = "0.13.0"
    const val mapForgeMap = "0.13.0"
    const val mapForgeThemes = "0.13.0"
    const val androidLegacy = "1.0.0"
}

object NetworkVersions {
    val retrofit = RetrofitVersions
    val okHttp = OkHttpVersions
}

object RetrofitVersions {
    const val base = "2.9.0"
    const val gsonConverter = "2.9.0"
}

object OkHttpVersions {
    const val base = "5.0.0-alpha.2"
    const val interceptor = "5.0.0-alpha.2"
    const val jackson = "2.11.1"
}

object AccompanistVersions {
    const val animation = "0.20.2"
    const val flowRow = "0.20.3"
    const val systemUiController = "0.24.2-alpha"
}

object TestVersions {
    const val barista = "4.0.0"
    const val testJunit = "4.12"
    const val testRunner = "1.1.1"
    const val testCore = "1.4.0"
    const val testMockk = "1.12.0"
    const val testUiAutomator = "2.2.0"
    const val testJunitExt = "1.1.0"
    const val expressoCore = "3.4.0"
}

object ComposeVersions {
    const val compose = "1.1.0"
    const val constraintLayout = "1.0.0-beta02"
    const val composeNav = "2.4.2"
    const val composeVm = "1.0.0-alpha07"
    const val composeActivity = "1.3.1"
}

object AndroidVersions {
    const val lifecycleRuntime = "2.4.1"
    const val lifecycleViewModel = "2.4.1"
    const val material = "1.3.0"
    const val ktx = "1.0.2"
    const val playCore = "1.10.0"
    const val dataStore = "1.0.0"
    const val prettyTime = "4.0.6.Final"
    const val glance = "1.0.0-alpha01"
    const val navigationRuntime = "2.4.1"
    val room = RoomVersions
    val hilt = HiltVersions
}

object RoomVersions {
    const val room = "2.4.0-alpha03"
    const val testRoom = "2.1.0"

}

object HiltVersions {
    const val navigation = "1.0.0"
    const val android = "2.41"
    const val androidCompiler = "2.41"
    const val compiler = "1.0.0"
}

object ImageLoaderVersions {
    const val coilCompose = "1.4.0"
    const val coilSvg = "1.3.2"
}

object GoogleServicesVersions {
    const val location = "17.0.0"
}

object PagingVersions {
    const val paging = "1.0.0-alpha14"

}

object Releases {
    const val versionCode = 5
    const val versionName = "1.0.3"
}