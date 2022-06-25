package top.panghai.multiavatar.controller;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import top.panghai.multiavatar.util.MultiAvatar;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Author: panghai
 * @Date: 2022/06/21/23:26
 * @Description:
 */
@Controller
public class WebController{

    private static final float WIDTH = 600;
    private static final float HEIGHT = 600;

    @GetMapping(value = "/**/{avatarId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public void getSVG(HttpServletResponse response, @PathVariable String avatarId) throws IOException, TranscoderException {
        String SVG = "";
        if (avatarId == null) {
            SVG = MultiAvatar.multiavatar(System.currentTimeMillis() + "");
        } else {
            avatarId = avatarId.replace(".png","");
            SVG = MultiAvatar.multiavatar(avatarId);
        }
        ByteArrayOutputStream pngStream = new ByteArrayOutputStream();
        InputStream pngInput;
        byte[] bytes = SVG.getBytes(StandardCharsets.UTF_8);
        TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
        PNGTranscoder t = new PNGTranscoder();

        //设置宽高
        t.addTranscodingHint(PNGTranscoder.KEY_WIDTH, WIDTH);
        t.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, HEIGHT);
        //格式转换
        t.transcode(input, new TranscoderOutput(pngStream));

        byte[] outByte = pngStream.toByteArray();
        pngInput = new ByteArrayInputStream(outByte);
        pngStream.close();
        response.setContentType("image/png");
        BufferedImage image = ImageIO.read(pngInput);
        OutputStream os = response.getOutputStream();
        if (image != null) {
            ImageIO.write(image, "png", os);
        }
    }
}
