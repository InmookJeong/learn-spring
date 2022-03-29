package kr.study.dev_mook.command;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import kr.study.dev_mook.dao.BDao;
import kr.study.dev_mook.dto.BDto;

public class BListCommand implements BCommand {
	
	private static final Logger logger = LoggerFactory.getLogger(BListCommand.class);
	
	
	@Override
	public void execute(Model model) {
		logger.info("Execute BListCommand.");
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
	}

}
