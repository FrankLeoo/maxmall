
package com.maxmall.provider.product.utils;

import com.google.common.collect.Lists;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.provider.product.model.vo.CategoryVo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * The class Tree util.
 *
 * @author maxmall.net@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryTreeUtil {
	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list     具有树形结构特点的集合
	 * @param parentId 父节点ID
	 *
	 * @return 树形结构集合 child menu vos
	 */
	public static List<CategoryVo> getChildCategoryVos(List<CategoryVo> list, Long parentId) {
		List<CategoryVo> returnList = Lists.newArrayList();

		for (CategoryVo menuVo : list) {
			if (menuVo.getParentId() == null) {
				continue;
			}
			if (Objects.equals(menuVo.getParentId(), parentId)) {
				recursionFn(list, menuVo);
				returnList.add(menuVo);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 */
	private static void recursionFn(List<CategoryVo> list, CategoryVo t) {
		// 得到子节点列表
		List<CategoryVo> childList = getChildList(list, t);
		t.setChildren(childList);
		if (PublicUtil.isNotEmpty(childList)) {
			t.setHasChild(true);
		}
		for (CategoryVo tChild : childList) {
			// 判断是否有子节点
			if (hasChild(list, tChild)) {
				for (CategoryVo n : childList) {
					recursionFn(list, n);
				}
				tChild.setHasChild(true);
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private static List<CategoryVo> getChildList(List<CategoryVo> list, CategoryVo t) {
		List<CategoryVo> tList = Lists.newArrayList();

		for (CategoryVo menuVo : list) {
			if (PublicUtil.isEmpty(menuVo.getParentId())) {
				continue;
			}
			if (Objects.equals(menuVo.getParentId(), t.getId())) {
				tList.add(menuVo);
			}
		}
		return tList;
	}

	/**
	 * 判断是否有子节点
	 */
	private static boolean hasChild(List<CategoryVo> list, CategoryVo t) {
		return !getChildList(list, t).isEmpty();
	}

}
