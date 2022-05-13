package kr.study.dev_mook.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.PageMaker;
import kr.study.dev_mook.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGet(BoardVO board, Model model) throws Exception {
		logger.info("##### Call registerGet");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("##### Call registerPost");
		logger.info(board.toString());
				
		service.regist(board);
		// Redirect를 해도 URL에 ?result=success가 남아있는 문제 발생
//		model.addAttribute("result", "success");
		
		// 리다이렉트 시점에 한 번만 사용되는 뎅터를 전송
		// 리다이렉트 후 URL 뒤에 ?msg=success가 보이지 않도록 함
		// key값을 result라고 하니까 jsession 값이 나옴. 이유는?
		rttr.addFlashAttribute("msg", "success");
		
		// 새로고침으로 인한 데이터 무분별한 등록 발생 가능
//		return "/board/success";
		
		// 등록 성공 후 바로 리다이렉트 페이지로 이동
		// 새로고침으로 인한 문제 해결 가능
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("##### Call listAll; show all list...");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("##### Call read.");
		model.addAttribute(service.read(bno));
	}
	
	/* 페이지 유지를 위한 ModelAttribute 추가 */
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("##### Call readPage.");
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGet(int bno, Model model) throws Exception {
		logger.info("##### Call modify get.");
		model.addAttribute(service.read(bno));
	}
	
	/* 페이지 유지를 위한 ModelAttribute 추가 */
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGet(int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("##### Call modify page get.");
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("##### Call modify post.");
		service.modify(board);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePost(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("##### Call modify post.");
		service.modify(board);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("##### Call remove.");
		service.remove(bno);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}
	
	/* 페이지 유지를 위한 Criteria 추가 */
	@RequestMapping(value = "/removePage", method = RequestMethod.GET)
	public String removePage(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("##### Call remove.");
		service.remove(bno);
		// 삭제 후 페이지 유지를 위한 Attribute 추가
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception {
		logger.info("##### Call listCri");
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {
		logger.info("##### Call listPage");
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
	}
	
}
