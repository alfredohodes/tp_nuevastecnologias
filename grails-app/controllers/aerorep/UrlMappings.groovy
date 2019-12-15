package aerorep

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/grails"(view:"/indexGrails")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
