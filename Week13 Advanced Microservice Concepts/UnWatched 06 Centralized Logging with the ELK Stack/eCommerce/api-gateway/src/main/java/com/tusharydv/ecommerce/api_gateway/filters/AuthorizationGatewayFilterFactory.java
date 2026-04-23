//package com.tusharydv.ecommerce.api_gateway.filters;
//
//import com.tusharydv.ecommerce.api_gateway.service.JwtService;
//import lombok.Data;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpStatus;
//
//import java.util.List;
//
//public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizationGatewayFilterFactory.Config> {
//
//
//    private final JwtService jwtService;
//
//    public AuthorizationGatewayFilterFactory(JwtService jwtService) {
//        super(AuthorizationGatewayFilterFactory.Config.class);
//        this.jwtService = jwtService;
//    }
//
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//
//            if(!config.isEnabled()) return chain.filter(exchange);
//
//            String authorizationHeader = exchange.getRequest()
//                    .getHeaders()
//                    .getFirst("Authorization");
//
//            if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//
//            String token = authorizationHeader.substring(7);
//
//            Long userId = jwtService.getUserIdFromToken(token);
//            List<String> roles = jwtService.getUserRoleFromToken(token);
//
//            // 🔥 AUTHORIZATION CHECK
//            if(config.getRequiredRole() != null && !roles.contains(config.getRequiredRole())){
//                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                return exchange.getResponse().setComplete();
//            }
//
//            exchange.getRequest()
//                    .mutate()
//                    .header("X-User-Id", userId.toString())
//                    .build();
//
//            return chain.filter(exchange);
//        };
//    }
//
//    @Data
//    public static class Config{
//        private boolean isEnabled;
//        private String requiredRole;
//    }
//}
