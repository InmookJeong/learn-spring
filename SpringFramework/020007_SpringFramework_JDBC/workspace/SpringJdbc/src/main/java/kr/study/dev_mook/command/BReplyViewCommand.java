package kr.study.dev_mook.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.study.dev_mook.dao.BDao;
import kr.study.dev_mook.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BDao dao = new BDao();
		BDto dto = dao.replyView(bId);
		
		model.addAttribute("reply_view", dto);
	}

}
