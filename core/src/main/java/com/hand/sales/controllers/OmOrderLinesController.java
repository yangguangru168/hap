package com.hand.sales.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.sales.dto.OmOrderLines;
import com.hand.sales.service.IOmOrderLinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OmOrderLinesController extends BaseController {

    @Autowired
    private IOmOrderLinesService service;
    @RequestMapping(value = "/hap/om/order/lines/queryLineAll")
    @ResponseBody
    public ResponseData queryLineAll(OmOrderLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectByLine(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/hap/om/order/lines/queryAll")
    @ResponseBody
    public ResponseData queryAll(OmOrderLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        if (dto.getHeaderId() == null) {
            List<OmOrderLines> list = new ArrayList<OmOrderLines>();
            return new ResponseData(list);
        }
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectByLine(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/hap/om/order/lines/query")
    @ResponseBody
    public ResponseData query(OmOrderLines dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/hap/om/order/lines/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<OmOrderLines> dto, BindingResult result, HttpServletRequest request) {
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage(getErrorMessage(result, request));
            return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hap/om/order/lines/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<OmOrderLines> dto) {
        service.batchDelete(dto);
        return new ResponseData();
    }
}