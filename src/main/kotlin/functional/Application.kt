package functional

import functional.web.UserHandler
import functional.web.routerApi
import functional.web.routerHtml
import functional.web.routerStatic

val application = webfluxApplication {

    // group routers
    routes {
        router { routerApi(ref()) }
        router(routerStatic())
    }
    router { routerHtml(ref(), ref()) }

    // group beans
    beans {
        bean<UserHandler>()
        bean<Baz>()
    }
    bean<Bar>()

    mustacheTemplate()

    profile("foo") {
        bean<Foo>()
    }
}


fun main(args: Array<String>) {
    application.run()
}

// Only for profile:"foo"
class Foo

class Bar
class Baz(val bar: Bar)
