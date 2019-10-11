package com.scrumboard.service;

import com.scrumboard.domain.Label;

public interface LabelService {

    Label newLabel(Long boardId, String name);
}
