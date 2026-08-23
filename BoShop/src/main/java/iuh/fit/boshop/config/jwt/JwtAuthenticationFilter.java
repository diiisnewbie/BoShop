package iuh.fit.boshop.config.jwt;

import iuh.fit.boshop.config.CustomeUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomeUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Lấy Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. Không có JWT → cho request đi tiếp
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Lấy token
        String token = authHeader.substring(7);

        // 4. Lấy email từ JWT
        String email = jwtService.extractUsername(token);

        // 5. Nếu chưa có Authentication
        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Tìm User trong database
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            // 7. Kiểm tra JWT
            if (jwtService.isTokenValid(token, userDetails)) {

                // 8. Tạo Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 9. Đưa Authentication vào SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }

        // 10. Cho request đi tiếp
        filterChain.doFilter(request, response);
    }
}