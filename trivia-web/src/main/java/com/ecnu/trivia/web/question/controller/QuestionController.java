/**
 * Controller编写规则：
 *   1、所有路径均按节划分，每一节均为 单个 名词
 *   2、路径结尾以反斜杠结束
 *   3、符合Restful接口规范
 *        GET：请求资源，不修改服务器数据
 *        POST：向服务器新增资源或修改资源
 *        DELETE：删除服务器资源
 *   4、返回结果均以Resp对象返回，框架统一转换为json
 *   5、代码中不得出现汉字，返回信息统一规范到HttpRespCode中
 *      若Resp中没有所需要的文字，请在ConstantsMsg中添加文字
 *   6、所有函数请在头部标明作者，以便代码回溯
 *   7、使用 @Resource 注解自动装配 Service
 *
 *   @author Jack Chen
 */
package com.ecnu.trivia.web.question.controller;

import com.ecnu.trivia.common.component.web.HttpRespCode;
import com.ecnu.trivia.common.util.ObjectUtils;
import com.ecnu.trivia.web.question.domain.Question;
import com.ecnu.trivia.web.question.service.QuestionService;
import com.ecnu.trivia.web.rbac.domain.vo.UserRegisterVO;
import com.ecnu.trivia.web.room.service.RoomService;
import com.ecnu.trivia.web.utils.Resp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/question", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {
    @Resource
    protected QuestionService questionService;

    /**
     * 为系统增加问题
     * @Author: Lucto
     * * @Date: 19:51 2017/12/17
     */
    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public Resp addQuestion(@RequestBody Question questionParam) {
        if (ObjectUtils.isNullOrEmpty(questionParam.getDescription()) || ObjectUtils.isNullOrEmpty(questionParam.getChooseA())
                ||  ObjectUtils.isNullOrEmpty(questionParam.getChooseB()) || ObjectUtils.isNullOrEmpty(questionParam.getChooseC())
                || ObjectUtils.isNullOrEmpty(questionParam.getChooseD()) ||ObjectUtils.isNullOrEmpty(questionParam.getAnswer())
                || ObjectUtils.isNullOrEmpty(questionParam.getTypeId())) {
            return new Resp(HttpRespCode.PARAM_ERROR);
        }
        questionService.addQuestion(questionParam.getDescription(),questionParam.getChooseA(),questionParam.getChooseB(),
                questionParam.getChooseC(),questionParam.getChooseD(),questionParam.getAnswer(),questionParam.getTypeId());
        return new Resp(HttpRespCode.SUCCESS);
    }
}
