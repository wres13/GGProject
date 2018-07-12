package com.dongdian.jj.gorgeous.home.view;

import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.tools.jj.tools.basemvp.v.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public interface IRecommendView extends BaseView {

   void showRecommendList(List<ImageData.ResultsBean> postListDtos);
}
