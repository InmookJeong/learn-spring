package kr.study.dev_mook.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.study.dev_mook.command.BCommand;
import kr.study.dev_mook.command.BContentCommand;
import kr.study.dev_mook.command.BDeleteCommand;
import kr.study.dev_mook.command.BListCommand;
import kr.study.dev_mook.command.BModifyCommand;
import kr.study.dev_mook.command.BReplyCommand;
import kr.study.dev_mook.command.BReplyViewCommand;
import kr.study.dev_mook.command.BWriteCommand;
import kr.study.dev_mook.util.Constants;

/**
 * MVC Board Controller
 */
@Controller
public class BController {
	
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	
	BCommand bCommand;
	
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// DB 접근을 위한 설정 완료
		Constants.jdbcTemplate = this.jdbcTemplate;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		logger.info("Call List Method.");
		
		bCommand = new BListCommand();
		bCommand.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String writeView(Model model) {
		logger.info("Call writeView()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		logger.info("Call write()");
		
		model.addAttribute("request", request);
		bCommand = new BWriteCommand();
		bCommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")
	public String contentView(HttpServletRequest request, Model model) {
		logger.info("Call contentView()");
		
		model.addAttribute("request", request);
		bCommand = new BContentCommand();
		bCommand.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		logger.info("Call modify()");
		
		model.addAttribute("request", request);
		bCommand = new BModifyCommand();
		bCommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String replyView(HttpServletRequest request, Model model) {
		logger.info("Call replyView()");
		
		model.addAttribute("request", request);
		bCommand = new BReplyViewCommand();
		bCommand.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(HttpServletRequest request, Model model) {
		logger.info("Call reply()");
		
		model.addAttribute("request", request);
		bCommand = new BReplyCommand();
		bCommand.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		logger.info("Call delete()");
		
		model.addAttribute("request", request);
		bCommand = new BDeleteCommand();
		bCommand.execute(model);
		
		return "redirect:list";
	}
}
