package com.jpa4.pj1984.service;

import com.jpa4.pj1984.domain.Display;
import com.jpa4.pj1984.dto.DisplayDTO;
import com.jpa4.pj1984.dto.DisplayForm;
import com.jpa4.pj1984.repository.DisplayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DisplayService {
    private final DisplayRepository displayRepository;
    private final FileUploadService fileUploadService;

    public DisplayDTO findOne() {
        Display display = displayRepository.findById(1L).orElse(null);
        return new DisplayDTO(display);
    }

    public void updateOne(DisplayForm displayForm) {
        Display display = displayRepository.findById(displayForm.getDisplayId()).orElse(null);
        display.setDisplayId(displayForm.getDisplayId());
    }
}
