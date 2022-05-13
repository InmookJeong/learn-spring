package kr.study.dev_mook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.study.dev_mook.model.ProductVO;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("doA")
	public void doA() {
		logger.info("##### Do A Call");
	}
	
	@RequestMapping("doB")
	public void doB() {
		logger.info("##### Do B Call");
	}
	
	@RequestMapping("doC")
	public String doC(@ModelAttribute("msg") String msg) {
		logger.info("##### Do C Call");
		return "result";
	}
	
	@RequestMapping("doD")
	public String doD(Model model) {
		logger.info("##### Do D Call");
		
		ProductVO product = new ProductVO("Sample Product", 10000);
		model.addAttribute(product);
		return "productDetail";
	}
	
	@RequestMapping("doE")
	public String doE(RedirectAttributes rttr) {
		logger.info("##### Do E Call");
		rttr.addFlashAttribute("msg", "This is the Message!! with redirected.");
		return "redirect:/doF";
	}
	
	@RequestMapping("doF")
	public void doF(String msg) {
		logger.info("##### Do F Call - msg : " + msg);
	}
	
	@RequestMapping("doJSON")
	public @ResponseBody ProductVO doJson() {
		logger.info("##### Do JSON Call");
		ProductVO vo = new ProductVO("샘플상품", 30000);
		return vo;
	}
	
}
