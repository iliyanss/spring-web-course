package bg.softuni.buildershop.error.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice
public class ImageUploadExceptionAdvice {
    private  final Logger logger;

    public ImageUploadExceptionAdvice() {
        logger = LoggerFactory.getLogger(ImageUploadExceptionAdvice.class);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exc, RedirectAttributes redirectAttributes) {
        logger.error("Image size handler: image is over limit", exc);
        redirectAttributes.addFlashAttribute("message", "File size exceeds the 10 MB limit!");
        return "redirect:/add-product";
    }
}