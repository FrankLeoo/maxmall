
package com.maxmall.provider.merchant.utils;

import com.google.common.collect.Lists;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.provider.merchant.model.domain.account.MenuDO;
import com.maxmall.provider.merchant.model.vo.MenuVo;
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
public class TreeUtil {
	/**
	 * 根据父节点的ID获取所有子节点
	 *
	 * @param list     具有树形结构特点的集合
	 * @param parentId 父节点ID
	 *
	 * @return 树形结构集合 child menu vos
	 */
	public static List<MenuVo> getChildMenuVos(List<MenuVo> list, Long parentId) {
		List<MenuVo> returnList = Lists.newArrayList();

		for (MenuVo menuVo : list) {
			if (menuVo.getPid() == null) {
				continue;
			}
			if (Objects.equals(menuVo.getPid(), parentId)) {
				recursionFn(list, menuVo);
				returnList.add(menuVo);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 */
	private static void recursionFn(List<MenuVo> list, MenuVo t) {
		// 得到子节点列表
		List<MenuVo> childList = getChildList(list, t);
		t.setSubMenu(childList);
		if (PublicUtil.isNotEmpty(childList)) {
			t.setHasMenu(true);
		}
		for (MenuVo tChild : childList) {
			// 判断是否有子节点
			if (hasChild(list, tChild)) {
				for (MenuVo n : childList) {
					recursionFn(list, n);
				}
				tChild.setHasMenu(true);
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private static List<MenuVo> getChildList(List<MenuVo> list, MenuVo t) {
		List<MenuVo> tList = Lists.newArrayList();

		for (MenuVo menuVo : list) {
			if (PublicUtil.isEmpty(menuVo.getPid())) {
				continue;
			}
			if (Objects.equals(menuVo.getPid(), t.getId())) {
				tList.add(menuVo);
			}
		}
		return tList;
	}

	/**
	 * 判断是否有子节点
	 */
	private static boolean hasChild(List<MenuVo> list, MenuVo t) {
		return !getChildList(list, t).isEmpty();
	}

}
