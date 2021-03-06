package org.cyclop.web.panels.queryeditor.export;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.cyclop.common.AppConfig;
import org.cyclop.model.CqlQuery;
import org.cyclop.model.CqlQueryResult;
import org.cyclop.service.exporter.CsvQueryResultExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/** @author Maciej Miklas */
public class QueryResultExport implements Serializable {

	private final static Logger LOG = LoggerFactory.getLogger(QueryResultExport.class);

	private final Downloader downloader;

	private CqlQuery query;

	private CqlQueryResult lastQueryResult;

	private final CsvQueryResultExporter exporter;

	private final static AppConfig.QueryExport conf = AppConfig.get().queryExport;

	public QueryResultExport(MarkupContainer parent, CsvQueryResultExporter exporter) {
		this.exporter = exporter;
		this.downloader = new Downloader();
		parent.add(downloader);
	}

	public void initiateDownload(AjaxRequestTarget target, CqlQuery query, CqlQueryResult lastQueryResult) {
		downloader.initiateDownload(target);
		this.query = query;
		this.lastQueryResult = lastQueryResult;
	}

	private final class Downloader extends DownloadBehavior {

		@Override
		protected String getFileName() {
			SimpleDateFormat formatter = new SimpleDateFormat(conf.fileNameDate);
			String fileName = conf.fileName.replace("DATE", formatter.format(new Date()));
			LOG.debug("CSV export file name: {}", fileName);
			return fileName;
		}

		@Override
		protected IResourceStream getResourceStream() {
			if (query == null || lastQueryResult == null) {
				return new StringResourceStream("No Data");
			}

			String csv = exporter.exportAsCsv(query, lastQueryResult);
			return new StringResourceStream(csv);

		}
	}

}
