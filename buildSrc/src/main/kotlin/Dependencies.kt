import org.gradle.api.artifacts.dsl.*

fun DependencyHandler.androidx(module: String, name: String?, version: String) =
	add("implementation", "androidx.$module:$module${name?.let { "-$it" } ?: ""}:$version")

fun DependencyHandler.androidx(module: String, version: String) = androidx(module, null, version)

fun DependencyHandler.androidxKtx(module: String, name: String?, version: String) =
	androidx(module, name?.let { "$it-ktx" } ?: "ktx", version)

fun DependencyHandler.androidxKtx(module: String, version: String) = androidxKtx(module, null, version)

fun DependencyHandler.okHttp(name: String?, version: String) =
	add("implementation", "com.squareup.okhttp3:${name ?: "okhttp"}:$version")

fun DependencyHandler.okHttp(version: String) = okHttp(null, version)

fun DependencyHandler.retrofit(name: String?, version: String) =
	add("implementation", "com.squareup.retrofit2:${name ?: "retrofit"}:$version")

fun DependencyHandler.retrofit(version: String) = retrofit(null, version)