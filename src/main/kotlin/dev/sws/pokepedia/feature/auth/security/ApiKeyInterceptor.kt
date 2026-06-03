package dev.sws.pokepedia.feature.auth.security

//@Component
//class ApiKeyInterceptor(
//    @Value("\${api.security.key}") private val apiKey: String
//): HandlerInterceptor {
//
//    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        if (request.method == "GET") {
//            val requestKey = request.getHeader("X-API-KEY")
//
//            if (requestKey == apiKey) {
//                return true
//            }
//
//            throw UnauthorizedException("Invalid or missing X-API-KEY header to read Pokedex data.")
//        }
//
//        return true
////        throw UnauthorizedException("Admin authentication is required to modify Pokedex data.")
//    }
//
//}