package com.Yanda.Ruitesco.service;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Category;
import com.Yanda.Ruitesco.utils.MessageResponse;

public interface ICategoryService {
	public MessageResponse<List<Category>> GetCategoryByParentId(int parent_id);
}
