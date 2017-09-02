package com.hellobbs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

@Controller
@RequestMapping("/images")
public class Imagecontroller {

    protected static Random random = new Random();

    protected static Color getBack() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    protected static Color getFront(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());

    }

    @RequestMapping("/captrue")
    @ResponseBody
    public String forcapture(HttpServletResponse response,HttpSession session) throws Exception {
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();


        String old = "23456789abcdefghijkmnpqrstuvwxyz"; //验证图片上面的随机字符
        StringBuffer sb = new StringBuffer();
        int j = 0;
        for (int i = 0; i < 4; i++) {
            j = random.nextInt(old.length());
            sb.append(old.substring(j, j + 1));
        }

        int width = 70;
        int height = 25;
        Color back = getBack();
        Color front = getFront(back);
        String code = sb.toString();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.setColor(back);
        g.fillRect(0, 0, width, height);
        g.setColor(front);
        g.drawString(code, 18, 20);
        for (int i = 0, n = random.nextInt(20); i < n; i++) {
            g.fillRect(random.nextInt(width), random.nextInt(height), 1, 1);

        }

        session.setAttribute("captruecode",sb.toString());


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpeg", outputStream);

        out.write(outputStream.toByteArray());
        outputStream.close();

        out.close();

        return "ok";
    }
}
