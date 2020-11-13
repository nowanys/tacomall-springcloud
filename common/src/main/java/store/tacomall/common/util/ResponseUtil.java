package store.tacomall.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger("ResponseUtil");

    public static void returnJson(HttpServletResponse response, Object object) {

        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(object));

        } catch (IOException e) {
            logger.error("response writer with error", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
