package com.hand.sales.controllers;

import com.hand.hap.code.rule.exception.CodeRuleException;
import com.hand.sales.dto.OmOrderLines;
import com.hand.sales.service.IOmOrderLinesService;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.sales.dto.OmOrderHeaders;
import com.hand.sales.service.IOmOrderHeadersService;
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
public class OmOrderHeadersController extends BaseController {

    @Autowired
    private IOmOrderLinesService linesService;

    @Autowired
    private IOmOrderHeadersService service;

    @RequestMapping(value = "/hap/om/order/headers/queryAll")
    @ResponseBody
    public ResponseData queryAll(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.queryAll(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/hap/om/order/headers/query")
    @ResponseBody
    public ResponseData query(OmOrderHeaders dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                              @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext, dto, page, pageSize));
    }

    @RequestMapping(value = "/hap/om/order/headers/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<OmOrderHeaders> dto, BindingResult result, HttpServletRequest request) throws CodeRuleException {
        getValidator().validate(dto, result);
        /*if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }*/
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.myBachSubmit(requestCtx, dto));
    }

    @RequestMapping(value = "/hap/om/order/headers/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request, @RequestBody List<OmOrderHeaders> dto) {
        IRequest requestContext = createRequestContext(request);
        service.batchDelete(dto);
        OmOrderLines omOrderLines = new OmOrderLines();
        omOrderLines.setHeaderId(dto.get(0).getHeaderId());
        List<OmOrderLines> omOrderLinesList = linesService.selectLineByheaderId(omOrderLines);
        linesService.batchDelete(omOrderLinesList);
        return new ResponseData(new ArrayList<>(0));
    }

    @RequestMapping(value = "/hap/om/order/headers/removeLine")
    @ResponseBody
    public ResponseData deleteLine(HttpServletRequest request, @RequestBody List<OmOrderHeaders> dto) throws CodeRuleException {
        if (dto != null && !dto.isEmpty()) {
            for (int i = 0; i < dto.size(); i++) {
                OmOrderHeaders omOrderHeaders = dto.get(i);
                Long headerId = omOrderHeaders.getHeaderId();
                OmOrderLines omOrderLines = new OmOrderLines();
                omOrderLines.setHeaderId(headerId);
                List<OmOrderLines> orderLinesList = linesService.selectLineAll(omOrderLines);
                linesService.batchDelete(orderLinesList);
            }
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.selectAllHead(requestCtx, dto));
    }
}