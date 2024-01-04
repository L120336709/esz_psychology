package com.sundata.edu.controller;

import java.io.*;
import java.util.*;
import java.util.List;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sundata.edu.domain.Invigilator;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.framework.web.result.AjaxResult;
import com.sundata.edu.framework.web.result.TableDataInfo;
import com.sundata.edu.service.IInvigilatorService;
import com.sundata.edu.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  信息操作处理
 * 
 * @author shunguo
 * @date 2021-12-10
 */
@Controller
@RequestMapping("/invigilator")
public class InvigilatorController extends BaseController
{
    private String prefix = "invigilator";
//    CUtil cutil = new CUtil();
	
	@Autowired
	private IInvigilatorService invigilatorService;
	
//	@RequiresPermissions("rfsj:invigilator:view")
	@GetMapping()
	public String invigilator()
	{
	    return prefix + "/invigilator";
	}
	
	/**
	 * 查询列表
	 */
//	@RequiresPermissions("rfsj:invigilator:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Invigilator invigilator)
	{
		startPage();
        List<Invigilator> list = invigilatorService.selectInvigilatorList(invigilator);
        for (int i=0;i<list.size();i++) {
            list.get(i).setPositionarea(list.get(i).getPositionarea()+list.get(i).getPositionsite()+list.get(i).getPositiongroup()+list.get(i).getPositionpeoplegroup()+list.get(i).getPositionpeoplecode());
        }
		return getDataTable(list);
	}

	/**
	 * 新增
	 */
	@GetMapping("/invigilatorRandom")
	public String invigilatorRandom()
	{

		return prefix + "/invigilatorRandom";
	}


	@RequestMapping("/exportCustomerFileDetailPDFReport")
	public void exportCustomerFileDetailPDFReport(HttpServletRequest request,HttpServletResponse response,String sessions) {
		//查询数据保存为字节数组输出流
		response.setHeader("Content-Type", "application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=jiankao.pdf");
		//System.err.println(sessions);
		Map<String,Map<String,List<String>>> namemap=randomSorting();
		ByteArrayOutputStream baos = this.exportCustomerFilePDFReport(namemap,sessions);

		OutputStream out = null;
		try {
			out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				baos.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public ByteArrayOutputStream exportCustomerFilePDFReport(Map<String,Map<String,List<String>>> namemaps,String sessions) {


		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document();
		Rectangle pageSize = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		//pageSize.rotate();
		document.setPageSize(pageSize);
		document.setMargins(0, 0, 30, 30);
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontChinese24 = new Font(bfChinese,18,Font.BOLD);

			PdfWriter.getInstance(document,baos);
			document.open();

			//加入空行
			Paragraph blankRow = new Paragraph(18f, " ", fontChinese24);
			document.add(blankRow);

			//获取key值，同时key对应考点、考务组代码
			String keys[]=new String[namemaps.size()];
			int keynum=0;
			for(String key:namemaps.keySet()){
				keys[keynum]=key;
				keynum++;
			}
			for (int m=0;m<namemaps.size();m++){
				Map<String,List<String>> namemap=namemaps.get(keys[m]);
				if(m!=0){
					document.newPage();
				}
				//System.err.println("keys["+m+"]"+keys[m]);

				int numall=namemap.size();
//                int num0=numall%2;
                int numleftstart=1,numleft=0,numrightstart=1,numright=0;
                int page=(numall/54)+1;
                for(int numpage=1;numpage<=page;numpage++){

                    document.add(blankRow);
                    //设置PDF标题头
					String titles="";
					if(keys[m].substring(0,3).equals("001")){
						titles="恩施职业技术学院";
					}else if(keys[m].substring(0,3).equals("002")){
						titles="恩施市第一中学";
					}
                    Paragraph titleRow = new Paragraph(18f, titles+"考点监考员安排表-第"+sessions+"场考试(考务"+keys[m].substring(3,6)+"组)", fontChinese24);
                    //设置居中
                    titleRow.setAlignment(Element.ALIGN_CENTER);
                    document.add(titleRow);
                    //加入空行
                    document.add(blankRow);

                    //制表人和日期
                    PdfPTable table = new PdfPTable(3);
                    float[] columnWidths = { 1.2f,0.1f,1.2f};
                    table.setWidths(columnWidths);
                    if(numpage<page){
                        numleftstart=54*(numpage-1)+1;
                        numleft=numleftstart+26;
                        numrightstart=numleft+1;
                        numright=numrightstart+26;
                    }else if(numpage==page){
                        numleftstart=54*(numpage-1)+1;
                        int numlastall=numall-54*(numpage-1);
                        int num00=numlastall%2;

                        if(numlastall==1){
                            numleft=numleftstart=1;
                            numrightstart=0;
                            numright=0;
                        }else {
                            if(num00==0){
                                numleft=numleftstart+(numlastall/2)-1;
                            }else {
                                numleft=numleftstart+(numlastall/2);
                            }
                            numrightstart=numleft+1;
                            numright=numall;
                        }


                    }
//                    System.err.println("numleftstart="+numleftstart+"  numleft="+numleft);
//                    System.err.println("numrightstart="+numrightstart+"  numright="+numright);
                    Font fontChinese13 = new Font(bfChinese,13,Font.BOLD);
                    Font fontChinese15 = new Font(bfChinese,13,Font.NORMAL);

                    PdfPTable table1 = new PdfPTable(3);
                    float[] columnWidths1 = { 0.4f,1.0f,1.0f};
                    table1.setWidths(columnWidths1);

                    PdfPCell pCell1 = new PdfPCell();
                    pCell1.setPhrase(new Phrase("序号",fontChinese13));
                    pCell1.setMinimumHeight(24); // 设置单元格高度
                    pCell1.setUseAscender(true); // 设置可以居中
                    pCell1.setHorizontalAlignment(pCell1.ALIGN_CENTER); // 设置水平居中
                    pCell1.setVerticalAlignment(pCell1.ALIGN_MIDDLE); // 设置垂直居中
                    table1.addCell(pCell1);
                    pCell1.setPhrase(new Phrase("监考员甲",fontChinese13));
                    table1.addCell(pCell1);
                    pCell1.setPhrase(new Paragraph("监考员乙",fontChinese13));
                    table1.addCell(pCell1);

                    for(int i=numleftstart;i<=numleft;i++){
                        pCell1.setPhrase(new Phrase(i+"",fontChinese15));
                        table1.addCell(pCell1);
                        for (String name:namemap.get((i-1)+"")){
                            pCell1.setPhrase(new Phrase(name,fontChinese15));
                            table1.addCell(pCell1);
                        }
                    }

                    PdfPTable table2 = new PdfPTable(3);
                    table2.setWidths(columnWidths1);
                    PdfPCell pCell2 = new PdfPCell();
                    pCell2.setPhrase(new Phrase("序号",fontChinese13));
                    pCell2.setMinimumHeight(24); // 设置单元格高度
                    pCell2.setUseAscender(true); // 设置可以居中
                    pCell2.setHorizontalAlignment(pCell2.ALIGN_CENTER); // 设置水平居中
                    pCell2.setVerticalAlignment(pCell2.ALIGN_MIDDLE); // 设置垂直居中
                    table2.addCell(pCell2);
                    pCell2.setPhrase(new Phrase("监考员甲",fontChinese13));
                    table2.addCell(pCell2);
                    pCell2.setPhrase(new Paragraph("监考员乙",fontChinese13));
                    table2.addCell(pCell2);
                    if(numrightstart!=0&&numright!=0){
                        for(int i=numrightstart;i<=numright;i++){
                            pCell2.setPhrase(new Phrase(i+"",fontChinese15));
                            table2.addCell(pCell2);
                            for (String name:namemap.get((i-1)+"")){
                                pCell2.setPhrase(new Phrase(name,fontChinese15));
                                table2.addCell(pCell2);
                            }
                        }
                    }



                    PdfPCell cells1 =new PdfPCell(table1);
                    table.addCell(cells1);

                    PdfPCell cell=new PdfPCell();
                    cell.setPhrase(new Phrase(" "));
                    cell.setRowspan(numleft);
                    table.addCell(cell);

                    PdfPCell cells2 =new PdfPCell(table2);
                    table.addCell(cells2);


                    document.add(table);


                }


			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			document.close();
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos;
	}




    public void exportPdf(String[][] teachname,String newfilename) {
        Document document=null;
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
            Font headFont = new Font(bfChinese, 10, Font.NORMAL);// 设置字体大小

            //第一步：创建一个document对象。
            document = new Document();
            //第二步：创建一个PdfWriter实例，将文件输出流指向一个文件。
            PdfWriter.getInstance(document, new FileOutputStream(newfilename));
            //第三步：打开文档。
            document.open();
            //Paragraph title = new Paragraph("你好，Pdf！", headFont);
			PdfPTable table = new PdfPTable(2);
			for (int aw = 0; aw < 10; aw++) {
				// 构建每一格
				table.addCell("cell"+aw);
			}
            //第四步：在文档中增加一个段落。
            //document.add(title);
			document.add(table);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(document!=null){
                //第五步：关闭文档。
                document.close();
            }
        }

    }


    public String[] quchong(String[] zu){
		//数组去重
		Set<String> treeSet = new TreeSet<String>();
		treeSet.addAll(Arrays.asList(zu));
		//set转换成数组
		int treeid=0;
		String[] Invigilators=new String[treeSet.size()];
		for (String tree:treeSet) {
			Invigilators[treeid]=tree;
			treeid++;
		}
		return  Invigilators;
	}

	/**
	 * 随机排序监考老师监考安排
	 * @param
	 * @return
	 */
	@PostMapping("/randomSortings")
	@ResponseBody
	public AjaxResult randomSortings()
	{
		Map<String,Map<String,List<String>>> namemap=randomSorting();
		String namem =namemap.size()+"";
		return success().put("data",namem);
	}



	public Map<String,Map<String,List<String>>> randomSorting()
	{
//		String newfilename="F:/sdtest/sd.pdf";
//		String[][] teachname=new String[10][2];
//        exportPdf(teachname,newfilename);

		List<Invigilator> invigilatorlist=invigilatorService.selectInvigilatorList(new Invigilator());

		//获取考点数量
		String[] kaodian=new String[invigilatorlist.size()];
		for(int i=0;i<invigilatorlist.size();i++){
			kaodian[i]=invigilatorlist.get(i).getPositionsite();
		}
		kaodian=quchong(kaodian);

		//获取数据并分考点获取考务组数量
		String[][] kaowuzu=new String[kaodian.length][invigilatorlist.size()];
		for(int i=0;i<invigilatorlist.size();i++){
			for(int j=0;j<kaodian.length;j++){
				if(kaodian[j].equals(invigilatorlist.get(i).getPositionsite())){
					kaowuzu[j][i]=invigilatorlist.get(i).getPositiongroup();
				}
			}
		}
		//去空值
		for(int i=0;i<kaowuzu.length;i++){
			List<String> list = new ArrayList<>(kaowuzu[i].length);;
			for(int j=0;j<kaowuzu[i].length;j++){
				list.add(kaowuzu[i][j]);
			}
			while (list.remove(null));
			while (list.remove(""));
			String []list2 = list.toArray(new String[list.size()]);
			kaowuzu[i]=list2;
			//去重
			kaowuzu[i]=quchong(kaowuzu[i]);
		}

		//二维的最大数量
		int num=0;
		for(int i=0;i<kaowuzu.length;i++){
			if(num<kaowuzu[i].length){
				num=kaowuzu[i].length;
			}
		}
		Map<String,String> names=new HashMap<>();
		//根据考点、考务组，获取每个考务组内的教师信息
		String[][][] jiaoshi=new String[kaodian.length][num][invigilatorlist.size()];
		for(int i=0;i<invigilatorlist.size();i++){
			for(int j=0;j<kaodian.length;j++){
				if(invigilatorlist.get(i).getPositionsite().equals(kaodian[j])){
					for(int h=0;h<kaowuzu[j].length;h++){
						if(invigilatorlist.get(i).getPositiongroup().equals(kaowuzu[j][h])){
							jiaoshi[j][h][i]=invigilatorlist.get(i).getPositionpeoplegroup();
							names.put(kaodian[j]+kaowuzu[j][h]+jiaoshi[j][h][i]+invigilatorlist.get(i).getPositionpeoplecode(),invigilatorlist.get(i).getTeachname());
						}
					}
				}
			}
		}

		//去空值
		for (int i=0;i<jiaoshi.length;i++){
			for (int j=0;j<jiaoshi[i].length;j++){
				List<String> list = new ArrayList<>(jiaoshi[i][j].length);;
				for(int h=0;h<jiaoshi[i][j].length;h++){
					list.add(jiaoshi[i][j][h]);
				}
				while (list.remove(null));
				while (list.remove(""));
				String []list2 = list.toArray(new String[list.size()]);
				jiaoshi[i][j]=list2;
				//去重
				jiaoshi[i][j]=quchong(jiaoshi[i][j]);
				jiaoshi[i][j]= invigilatorService.randomSorting(jiaoshi[i][j]);
			}
		}
		Map<String,Map<String,List<String>>> maplist=new HashMap<>();

		int numb=0;
		for (int i=0;i<jiaoshi.length;i++) {
			for (int j = 0; j < jiaoshi[i].length; j++) {
				String maplistkey="";
				Map<String,List<String>> namemap=new HashMap<>();
				for (int h = 0; h < jiaoshi[i][j].length; h++) {
					List<String> allteach=new ArrayList<>();
					for (String key:names.keySet()) {
						if(key.substring(0,9).equals(kaodian[i]+kaowuzu[i][j]+jiaoshi[i][j][h])){
							allteach.add(names.get(key));
							maplistkey=kaodian[i]+kaowuzu[i][j];
						}
					}

					namemap.put(h+"",allteach);
				}
				//System.err.println("maplistkey====="+maplistkey);
				maplist.put(maplistkey,namemap);
				numb++;
			}
		}

		return maplist;
	}

	/**
	 * 导入教师资格证考场管理列表
	 */
	@PostMapping("/importData")
	@ResponseBody
	public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
	{
		ExcelUtil<Invigilator> util = new ExcelUtil<Invigilator>(Invigilator.class);
		List<Invigilator> InvigilatorList = util.importExcel(file.getInputStream());
		String message = invigilatorService.InvigilatorListImport(InvigilatorList, updateSupport);
		return AjaxResult.success(message);
	}
	/**
	 * 下载教师资格证考场管理导入模板
	 */
	@GetMapping("/importTemplate")
	@ResponseBody
	public AjaxResult importTemplate()
	{
		ExcelUtil<Invigilator> util = new ExcelUtil<Invigilator>(Invigilator.class);

		return util.importTemplateExcel("用户数据");
	}

	/**
	 * 导出列表
	 */
//	@RequiresPermissions("rfsj:invigilator:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Invigilator invigilator)
    {
    	List<Invigilator> list = invigilatorService.selectInvigilatorList(invigilator);
        ExcelUtil<Invigilator> util = new ExcelUtil<Invigilator>(Invigilator.class);
        return util.exportExcel(list, "invigilator");
    }
	
	/**
	 * 新增
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存
	 */
//	@RequiresPermissions("rfsj:invigilator:add")
//	@Log(title = "新增", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Invigilator invigilator)
	{		
		//invigilator.setID(cutil.Guid());
		return toAjax(invigilatorService.insertInvigilator(invigilator));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{teachid}")
	public String edit(@PathVariable("teachid") String teachid, ModelMap mmap)
	{
		Invigilator invigilator = invigilatorService.selectInvigilatorById(teachid);
		mmap.put("invigilator", invigilator);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存
	 */
//	@RequiresPermissions("rfsj:invigilator:edit")
//	@Log(title = "修改", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Invigilator invigilator)
	{		
		return toAjax(invigilatorService.updateInvigilator(invigilator));
	}
	
	/**
	 * 删除
	 */
//	@RequiresPermissions("rfsj:invigilator:remove")
//	@Log(title = "删除", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(invigilatorService.deleteInvigilatorByIds(ids));
	}
	
}