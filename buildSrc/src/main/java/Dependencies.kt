object Dependencies {
    val android = AndroidDependencies
    val coroutines = CoroutinesDependencies
    val compose = ComposeDependencies
    val test = TestDependencies
    val accompanist = AccompanistDependencies
    val network = NetworkDependencies
    val imageLoader = ImageLoaderDependencies
    val paging = PagingDependencies
    val googleServices = GoogleServicesDependencies
    val map = MapDependencies
}

object MapDependencies{
    const val osmdroid = "org.osmdroid:osmdroid-android:${Versions.map.osmdroid}"
    const val osmdroidMapForge= "org.osmdroid:osmdroid-mapsforge:${Versions.map.osmdroidMapForge}"
    const val mapForgeMapAndroid = "org.mapsforge:mapsforge-map-android:${Versions.map.mapForgeMapAndroid}"
    const val mapForgeMap = "org.mapsforge:mapsforge-map:${Versions.map.mapForgeMap}"
    const val mapForgeThemes = "org.mapsforge:mapsforge-themes:${Versions.map.mapForgeThemes}"
    const val androidLegacy = "androidx.legacy:legacy-support-v4:${Versions.map.androidLegacy}"
}

object GoogleServicesDependencies {
    const val location =
        "com.google.android.gms:play-services-location:${Versions.googleServices.location}"
}

object PagingDependencies {
    const val compose = "androidx.paging:paging-compose:${Versions.paging.paging}"
}

object ImageLoaderDependencies {
    const val compose = "io.coil-kt:coil-compose:${Versions.imageLoader.coilCompose}"
    const val svg = "io.coil-kt:coil-svg:${Versions.imageLoader.coilSvg}"
}

object AndroidDependencies {
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.android.lifecycleRuntime}"
    const val lifecycleViewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.android.lifecycleRuntime}"
    const val material = "com.google.android.material:material:${Versions.android.material}"
    const val ktx = "androidx.core:core-ktx:${Versions.android.ktx}"
    const val playCore = "com.google.android.play:core:${Versions.android.playCore}"
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.android.dataStore}"
    const val prettyTime = "org.ocpsoft.prettytime:prettytime:${Versions.android.prettyTime}"
    const val glance = "androidx.glance:glance-appwidget:${Versions.android.glance}"
    const val navigationRuntime =
        "androidx.navigation:navigation-runtime-ktx:${Versions.android.navigationRuntime}"
    val room = RoomDependencies
    val hilt = HiltDependencies
}

object NetworkDependencies {
    val retrofit = RetrofitDependencies
    val okHttp = OkHttpDependencies
}

object OkHttpDependencies {
    const val base = "com.squareup.okhttp3:okhttp:${Versions.network.okHttp.base}"
    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.network.okHttp.interceptor}"
    const val jackson =
        "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.network.okHttp.jackson}"
}

object RetrofitDependencies {
    const val base = "com.squareup.retrofit2:retrofit:${Versions.network.retrofit.base}"
    const val gsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.network.retrofit.gsonConverter}"
}

object HiltDependencies {
    const val navigation = "androidx.hilt:hilt-navigation-compose:${Versions.android.hilt.navigation}"
    const val android = "com.google.dagger:hilt-android:${Versions.android.hilt.android}"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.android.hilt.androidCompiler}"
    const val compiler = "androidx.hilt:hilt-compiler:${Versions.android.hilt.compiler}"
}

object CoroutinesDependencies {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object RoomDependencies {
    const val runtime = "androidx.room:room-runtime:${Versions.android.room.room}"
    const val   compiler = "androidx.room:room-compiler:${Versions.android.room.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.android.room.room}"
}

object ComposeDependencies {
    const val ui = "androidx.compose.ui:ui:${Versions.compose.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose.compose}"
    const val googleMaterial = "com.google.android.material:material:1.4.0"
    const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose.compose}"
    const val icons =
        "androidx.compose.material:material-icons-extended:${Versions.compose.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.compose.composeNav}"
    const val viewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.compose.composeVm}"
    const val activity = "androidx.activity:activity-compose:${Versions.compose.composeActivity}"
    const val uiTest = "androidx.compose.ui:ui-test:${Versions.compose.compose}"
    const val junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose.compose}"
    const val manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose.compose}"
    const val uiToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose.compose}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.compose.constraintLayout}"
    const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose.compose}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.test.testJunit}"
    const val runner = "androidx.test:runner:${Versions.test.testRunner}"
    const val core = "androidx.test:core:${Versions.test.testCore}"
    const val coreKtx = "androidx.test:core-ktx:${Versions.test.testCore}"
    const val uiAutomator = "androidx.test.uiautomator:uiautomator:${Versions.test.testUiAutomator}"
    const val junitExt = "androidx.test.ext:junit:${Versions.test.testJunitExt}"
    const val mockk = "io.mockk:mockk:${Versions.test.testMockk}"
    const val room = "androidx.room:room-testing:${Versions.android.room.testRoom}"
    const val barista = "com.adevinta.android:barista:${Versions.test.barista}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.test.expressoCore}"
}

object AccompanistDependencies {
    const val animation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist.animation}"
    const val flowRow =
        "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist.flowRow}"
    const val systemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist.systemUiController}"
}