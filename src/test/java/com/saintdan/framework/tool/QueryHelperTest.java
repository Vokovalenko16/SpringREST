package com.vokovalenko16.framework.tool;

import com.vokovalenko16.framework.tools.QueryHelper;
import org.junit.Test;
import org.springframework.data.domain.Sort;

/**
 * Test case for {@link com.vokovalenko16.framework.tools.QueryHelper}
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 7/5/16
 * @since JDK1.8
 */
public class QueryHelperTest {

  @Test
  public void testGetSort() throws Exception {
    Sort sort = QueryHelper.getSort(sortBy);
    System.out.println(sort);
  }

  private static final String sortBy = "id:asc,name:desc,date:desc";
}
