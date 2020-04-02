package server.eureka.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @author siss
 * @date 2020/4/2  13:19
 * @description 以bse64的方式加密配置文件security密码, rawPassword为前端输入密码
 * encodedPassword为配置文件配置密码
 */
public class CustomerPasswordEncode extends BCryptPasswordEncoder {
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            try {
                BASE64Decoder decoder = new BASE64Decoder();
                byte[] bytes = decoder.decodeBuffer(encodedPassword);
                String decoderPassword = new String(bytes);
                if(decoderPassword.equals(rawPassword.toString())) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return super.matches(rawPassword,encodedPassword);
    }
}
