package com.ditto.cookiez.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ditto.cookiez.service.IRecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Zhihao Liang
 * @date 2020/9/23 20:12
 * @email s3798366@student.rmit.edu.au
 */
@SpringBootTest
class RecipeServiceImplTest {
    @Autowired
    IRecipeService service;

    @Test
    void updateRecipe() {
    }

    @Test
    void addRecipe() {
      JSONObject jsonObject= (JSONObject) JSONObject.parse("{\n" +
              "  \"recipe\": {\n" +
              "    \"recipeDescription\": \"s#Fv97ONnFjE4S7jqAYWDhEnLBpLzIbQGktzPRuGME3D[lXHOrwT@7gzm9@[z[NCxQvDo\",\n" +
              "    \"recipeName\": \"Rx@4\",\n" +
              "    \"recipeCreatedTime\": \"2018-04-24\"\n" +
              "  },\n" +
              "  \"steps\": [\n" +
              "    {\n" +
              "      \"stepOrder\": 7,\n" +
              "      \"stepContent\": \"t@s0kGoLOctE8PifwmTUo0jhTY9bylgwKOv6e@I9DA[k@S0ReLfO9OqDmfzc7j1!3AtUoPIpBU171qx@[C0jMi7JL08Vp)]tDhBaRS79pIjaMynL63MDcQKhHr3[z68&jF)h%ju!n9HI8Z^OVjG)Hd(I#qv8V#4m(dnE0fty[DZ)i]r9oe]Ajo\",\n" +
              "      \"imgPath\": \"RUV%QBYSfKlI^f6k32wp$NSnn*iMOckKo4VpuJQZ7R&CIx2s57HS&ItAHZ7ioR%$xDJAui87AvNHZoyJF4Non*9\"\n" +
              "    },\n" +
              "    {\n" +
              "      \"stepOrder\": 2,\n" +
              "      \"stepContent\": \"G[0yoVmGhFZzcM6hbmchE@BkDhvxV]@u3G0NpQzcqoS!%VyL!e3l4KGgdQ3eHdd#k78Fy(QgbLnnPEn7VnGmbK(Wh&kQfE2#^fc5QL[8Cd%0M7AZV7&kSB(dIhj2B2%wD2IT#QQIHkxG9iBYNyCITKxuzLc[U%%bs#0F9ayuTk50m8\",\n" +
              "      \"imgPath\": \"rsPwwJIsN*O3bryJAqJZtrd\"\n" +
              "    },\n" +
              "    {\n" +
              "      \"stepOrder\": 4,\n" +
              "      \"stepContent\": \"IfbxY2RSk33uY4F&m5&b3XUe\",\n" +
              "      \"imgPath\": \"klrQc&PFYfJAC4M3wjRsw&KmrI4&^KxGXC!C7n@3JepX]0$Lqmgo3kM4Ty$B!#vOYXo]K(IdQajxddF8tA!Xu&QbKDO@rEamfNvSecsk%kjH#$(5f&cOXqDw!Q66LjokkOf\"\n" +
              "    },\n" +
              "    {\n" +
              "      \"stepOrder\": 5,\n" +
              "      \"stepContent\": \"122KHtqWkFJeozqvzkTn2)9Smeif^XUInhKTnW2nrALl)ZS0\",\n" +
              "      \"imgPath\": \"U%CF3wZ#!)iXQ7&dOA%pV$FQ[CJFH5#0cJY(NfYQdQml6B7VkATOibvMts37LxpKhSv8$P1TLY6&%ClCW\"\n" +
              "    },\n" +
              "    {\n" +
              "      \"stepOrder\": 3,\n" +
              "      \"stepContent\": \"Rgl%hmK5xP!%9ci!)r8@!hs525t0Sd*Vq5haS!P%j515qRi38l78vXocT4js&1P2t3OO96okdTBkjD0H#*JnR(jtLhNdT*![4I#$Vf@ogHOd7$gca\",\n" +
              "      \"imgPath\": \"M0!lpzS&byjFS%7kUai(ewuo(!@U5fF]Wm]Cm!o^0XH7bI@sZT#dvju9yAEwuvB5[(SrjIVfUGsVCfn$xbZD0v^lycLuknos[yUeXJ0X&ZQKR1yL2R#3wX2WI@I[\"\n" +
              "    }\n" +
              "  ]\n" +
              "}");
        System.out.println(jsonObject.toJSONString());
//        servic.addRecipe(jsonObject);
    }

    @Test
    void testUpdateRecipe() {
    }

    @Test
    void testAddRecipe() {
    }

    @Test
    void getRecipe() {
        Integer id=19;
        System.out.println(   service.getRecipe(19).toString());

    }
}