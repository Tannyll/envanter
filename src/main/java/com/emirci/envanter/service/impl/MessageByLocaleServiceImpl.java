package com.emirci.envanter.service.impl;

import java.util.Locale;

import com.emirci.envanter.service.MessageByLocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by serdaremirci on 9/23/17.
 */

@Service
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

    @Autowired
    private MessageSource messageSource;


    @Override
    public String getMessage(String key) {

        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, null, locale);
    }
}
