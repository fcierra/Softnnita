/**Consulta los menus disponibles por perfiles*/
SELECT menu.ID_MENU, menu.MENU_LABEL,menu.ORDEN FROM ISOFT_MENUS_PERFIL menuperfil
	INNER JOIN ISOFT_MENU menu ON menu.ID_MENU = menuperfil.ID_MENU
	WHERE menuperfil.ID_PERFIL = 1 
	AND menu.habilitado = 1;
/**Consulta los menus item disponibles por un menu padre y un perfil */	
SELECT item.MENU_LABEL,item.REF_SECURITY FROM isoft_menu_item item
  INNER JOIN isoft_permisos ip on item.ID_MENU_ITEM = ip.ID_MENU_ITEM
  WHERE item.ID_MENU_PADRE = 3
  AND item.HABILITADO = 1
  AND ip.ID_PERFIL = 1;