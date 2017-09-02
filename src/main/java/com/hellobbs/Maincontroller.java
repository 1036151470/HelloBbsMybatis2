package com.hellobbs;

import com.hellobbs.database.Boardcontext;
import com.hellobbs.database.Boardcontextwith;
import com.hellobbs.database.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class Maincontroller {

    @Autowired
    SqlSession sqlSession;

    @GetMapping("aboutme")
    public String aboutme(Map<String,Object> map){
        Subject currentUserId = SecurityUtils.getSubject();
        User user = (User) currentUserId.getPrincipal();
        User user2 = sqlSession.selectOne("mainmapper.getuser", user.getUsername());
        map.put("justside", user2);

        return "aboutme";
    }

    @PostMapping("/reguser")
    public String postreguser(String username, String password, String roles, String userpics, String messages, HttpSession session,HttpServletRequest request,Map<String,Object>map) {
        HttpSession getsession = request.getSession();
        String captruecode = (String) getsession.getAttribute("captruecode");
        String verification = request.getParameter("captruecode");
        if (!verification.contentEquals(captruecode)) {
            return "redirect:/error";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserroles(roles);
        user.setUserpics(userpics);
        user.setMessages(messages);
        sqlSession.insert("mainmapper.reguser",user);
        map.put("ismessage","");
        return "reguser";
    }

    @GetMapping("/reguser")
    public String reguser() {
        return "reguser";
    }

    @PostMapping("/yourselfmessages")
    public String postyourselfmessages(String username, String password, String roles, String userpics, String messages,Map<String,Object>map) {
        User user = new User();
        if (username.length()<=0){
            map.put("ismessage","");
            return "yourselfmessages";
        }
        if (password.length()<=0){
            map.put("ismessage","");
            return "yourselfmessages";
        }
        if (roles.length()<=0){
            map.put("ismessage","");
            return "yourselfmessages";
        }
        if (userpics.length()<=0){
            map.put("ismessage","");
            return "yourselfmessages";
        }
        if(messages.length()<=0){
            map.put("ismessage","");
            return "yourselfmessages";
        }

        Subject currentUserId = SecurityUtils.getSubject();
        User getuser = (User) currentUserId.getPrincipal();
        User getuser2 = sqlSession.selectOne("mainmapper.getuser",getuser.getUsername());
        user.setId(getuser2.getId());
        user.setMessages(messages);
        user.setUserpics(userpics);
        user.setPassword(password);
        user.setUsername(username);
        user.setUserroles(roles);
        sqlSession.update("mainmapper.changesuser",user);
        return "redirect:/isok";
    }

    @GetMapping("/isok")
    public String isok() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "isok";
    }


    @GetMapping("/yourselfmessages")
    public String yourselfmessages(Map<String,Object>map) {
        Subject currentUserId = SecurityUtils.getSubject();
        User user = (User) currentUserId.getPrincipal();
        User user2 = sqlSession.selectOne("mainmapper.getuser", user.getUsername());
        map.put("justside", user2);

        return "yourselfmessages";
    }


    //登出
    @RequestMapping("/logout")
    @ResponseBody
    public String logout() {
        String str;

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        if (subject.isAuthenticated()) {
            str = "已登出";
        } else {
            str = "错误";
        }
        return str;
    }

    //登录，同名, 参数不同
    @GetMapping("/login")
    public String login() {
        try {
            Subject currentUserId = SecurityUtils.getSubject();
            User user = (User) currentUserId.getPrincipal();
            user.getUsername();
            return "redirect:/index";
        } catch (Exception e) {
            return "login";
        }
    }

    @PostMapping("/errorcode")
    public String errorcode(Map<String, Object> map) {
        map.put("ismessage", "");
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        if (exception != null) {
            map.put("ismessage", "");
            return "login";
        }
        return "/index";
    }


    @PostMapping("/wirtedatahtml")
    public String postwirtedatahtml(String title, String con, String selectinput,String justforurl) {
        User user = null;
        try {
            Subject currentUserId = SecurityUtils.getSubject();
            user = (User) currentUserId.getPrincipal();
            user.getUsername();
        } catch (Exception e) {
            System.out.print("er");
            return "/login";
        }
        Boardcontext c = new Boardcontext();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        c.setCreatedtime(sdf.format(d));
        c.setTitle(title);
        c.setContext(con);
        c.setUser(user.getUsername());
        sqlSession.insert(selectinput, c);
        return "redirect:/"+justforurl;
    }

    @PostMapping("/wirtedatahtmlwith")
    public String postwirtedatahtmlwith(String con, int withc, String justforurl,String intodbwith) {
        User user = null;
        try {
            Subject currentUserId = SecurityUtils.getSubject();
            user = (User) currentUserId.getPrincipal();
            user.getUsername();
        } catch (Exception e) {
            System.out.print("e");
            return "/login";
        }

        Boardcontextwith c = new Boardcontextwith();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        c.setCreatedtime(sdf.format(d));
        c.setContext(con);
        c.setWithc(withc);
        c.setUser(user.getUsername());
        sqlSession.insert(intodbwith, c);
        return "redirect:" + justforurl;
    }


    @GetMapping("/wirtedatahtml")
    public String wirtedatahtml(Map<String, Object> map) {
        try {
            Subject currentUserId = SecurityUtils.getSubject();
            User user = (User) currentUserId.getPrincipal();
            User user2 = sqlSession.selectOne("mainmapper.getuser", user.getUsername());
            map.put("justside", user2);
        } catch (Exception e) {
            return "/login";
        }

        return "wirtedatahtml";
    }

    @RequestMapping({"/", "/index"})
    public String index(Map<String, Object> map) {
        try {
            Subject currentUserId = SecurityUtils.getSubject();
            User user = (User) currentUserId.getPrincipal();
            User user2 = sqlSession.selectOne("mainmapper.getuser", user.getUsername());
            map.put("justside", user2);
        } catch (Exception e) {
            System.out.print("  ");
        }

        return "index";
    }

}
