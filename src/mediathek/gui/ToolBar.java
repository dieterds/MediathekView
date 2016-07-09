/*
 * MediathekView
 * Copyright (C) 2008 W. Xaver
 * W.Xaver[at]googlemail.com
 * http://zdfmediathk.sourceforge.net/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package mediathek.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.Box.Filler;
import javax.swing.*;
import mSearch.filmeSuchen.ListenerFilmeLaden;
import mSearch.filmeSuchen.ListenerFilmeLadenEvent;
import mSearch.tool.Listener;
import mSearch.tool.MVConfig;
import mediathek.daten.Daten;
import mediathek.res.GetIcon;
import mediathek.tool.Filter;
import org.jdesktop.swingx.JXSearchField;

public final class ToolBar extends JToolBar {

//switch (state) {
//case TOOLBAR_TAB_FILME:
//    break;
//case TOOLBAR_TAB_DOWNLOADS:
//    break;
//case TOOLBAR_TAB_ABOS:
//    break;
//}
    public static final String TOOLBAR_NIX = "";
    public static final String TOOLBAR_TAB_FILME = "Tab-Filme";
    public static final String TOOLBAR_TAB_DOWNLOADS = "Tab-Downloads";
    public static final String TOOLBAR_TAB_ABOS = "Tab-Abos";
    public static final String TOOLBAR_TAB_MELDUNGEN = "Meldungen";
    Filler filler__5;
    Filler filler__10;
    Filler filler__trenner;
    MVButton jButtonAboAendern = null;
    MVButton jButtonAbosAusschalten = null;
    MVButton jButtonAbosEinschalten = null;
    MVButton jButtonAbosLoeschen = null;
    MVButton jButtonDownloadAktualisieren = null;
    MVButton jButtonDownloadAlleStarten = null;
    MVButton jButtonDownloadAufraeumen = null;
    MVButton jButtonDownloadFilmStarten = null;
    MVButton jButtonDownloadLoeschen = null;
    MVButton jButtonDownloadZurueckstellen = null;
    MVButton jButtonFilmAbspielen = null;
    MVButton jButtonFilmSpeichern = null;
    MVButton jButtonFilmlisteLaden = null;
    JButton jButtonFilterPanel = null;
    MVButton jButtonInfo = null;
    public JXSearchField jTextFieldFilter;

    private String nrToolbar = "";
    private String nrIconKlein = "";
    private final Daten daten;
    BeobMausToolBar beobMausToolBar = new BeobMausToolBar();
    LinkedList<MVButton> buttonListFilme = new LinkedList<>();
    LinkedList<MVButton> buttonListDownloads = new LinkedList<>();
    LinkedList<MVButton> buttonListAbos = new LinkedList<>();
    boolean extern = false;
    String state;
    LinkedList<MVButton> buttonListToUse;

    public ToolBar(Daten ddaten, String state) {
        // für die Toolbar der Externen Fenster
        extern = true;
        daten = ddaten;
        this.state = state;
        switch (state) {
            case TOOLBAR_TAB_FILME:
                nrToolbar = MVConfig.SYSTEM_TOOLBAR_FILME;
                nrIconKlein = MVConfig.SYSTEM_ICON_KLEIN_FILME;
                buttonListToUse = buttonListFilme;
                break;
            case TOOLBAR_TAB_DOWNLOADS:
                nrToolbar = MVConfig.SYSTEM_TOOLBAR_DOWNLOAD;
                nrIconKlein = MVConfig.SYSTEM_ICON_KLEIN_DOWNLOAD;
                buttonListToUse = buttonListDownloads;
                break;
            case TOOLBAR_TAB_ABOS:
                nrToolbar = MVConfig.SYSTEM_TOOLBAR_ABO;
                nrIconKlein = MVConfig.SYSTEM_ICON_KLEIN_ABO;
                buttonListToUse = buttonListAbos;
                break;
            default:
                nrToolbar = "";
                nrIconKlein = "";
                buttonListToUse = new LinkedList<>();
        }
        startup();
        setToolbar(state);
    }

    private void startup() {
        // init
        this.setBackground(new java.awt.Color(204, 204, 204));
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        this.setFloatable(false);
        filler__5 = new Filler(new java.awt.Dimension(5, 20), new java.awt.Dimension(5, 20), new java.awt.Dimension(5, 32767));
        filler__10 = new Filler(new java.awt.Dimension(10, 20), new java.awt.Dimension(10, 20), new java.awt.Dimension(10, 32767));
        filler__trenner = new javax.swing.Box.Filler(new java.awt.Dimension(1, 5), new java.awt.Dimension(1, 5), new java.awt.Dimension(32767, 5));

        switch (state) {
            case TOOLBAR_TAB_FILME:
                jButtonFilmlisteLaden = new MVButton(new String[]{TOOLBAR_TAB_FILME, TOOLBAR_TAB_DOWNLOADS}, "Filmliste laden", "neue Filmliste laden", "filmlisteLaden_32.png", "filmlisteLaden_16.png");
                buttonListFilme.add(jButtonFilmlisteLaden);
                jButtonInfo = new MVButton(new String[]{TOOLBAR_TAB_FILME, TOOLBAR_TAB_DOWNLOADS}, "Filminformation anzeigen", "Filminformation anzeigen", "info_32.png", "info_16.png");
                buttonListFilme.add(jButtonInfo);
                jButtonFilmAbspielen = new MVButton(new String[]{TOOLBAR_TAB_FILME}, "Film abspielen", "Film abspielen", "film_start_32.png", "film_start_16.png");
                buttonListFilme.add(jButtonFilmAbspielen);
                jButtonFilmSpeichern = new MVButton(new String[]{TOOLBAR_TAB_FILME}, "Film aufzeichnen", "Film aufzeichnen", "film_rec_32.png", "film_rec_16.png");
                buttonListFilme.add(jButtonFilmSpeichern);
                break;
            case TOOLBAR_TAB_DOWNLOADS:
                jButtonInfo = new MVButton(new String[]{TOOLBAR_TAB_FILME, TOOLBAR_TAB_DOWNLOADS}, "Filminformation anzeigen", "Filminformation anzeigen", "info_32.png", "info_16.png");
                buttonListFilme.add(jButtonInfo);
                jButtonDownloadAktualisieren = new MVButton(new String[]{TOOLBAR_TAB_DOWNLOADS}, "Liste der Downloads aktualisieren", "Liste der Downloads aktualisieren", "view-refresh_32.png", "view-refresh_16.png");
                buttonListDownloads.add(jButtonDownloadAktualisieren);
                jButtonDownloadAlleStarten = new MVButton(new String[]{TOOLBAR_TAB_DOWNLOADS}, "alle Downloads starten", "alle Downloads starten", "download_alleStarten_32.png", "download_alleStarten_16.png");
                buttonListDownloads.add(jButtonDownloadAlleStarten);
                jButtonDownloadFilmStarten = new MVButton(new String[]{TOOLBAR_TAB_DOWNLOADS}, "Film Starten", "gespeicherten Film abspielen", "film_start_32.png", "film_start_16.png");
                buttonListDownloads.add(jButtonDownloadFilmStarten);
                jButtonDownloadZurueckstellen = new MVButton(new String[]{TOOLBAR_TAB_DOWNLOADS}, "Downloads zurückstellen", "Downloads zurückstellen", "undo_32.png", "undo_16.png");
                buttonListDownloads.add(jButtonDownloadZurueckstellen);
                jButtonDownloadLoeschen = new MVButton(new String[]{TOOLBAR_TAB_DOWNLOADS}, "Downloads aus Liste entfernen", "Downloads aus Liste entfernen", "download_del_32.png", "download_del_16.png");
                buttonListDownloads.add(jButtonDownloadLoeschen);
                jButtonDownloadAufraeumen = new MVButton(new String[]{TOOLBAR_TAB_DOWNLOADS}, "Liste der Downloads aufräumen", "Liste der Downloads aufräumen", "download_clear_32.png", "download_clear_16.png");
                buttonListDownloads.add(jButtonDownloadAufraeumen);
                break;
            case TOOLBAR_TAB_ABOS:
                jButtonAbosEinschalten = new MVButton(new String[]{TOOLBAR_TAB_ABOS}, "Abos einschalten", "Abos einschalten", "ja_32.png", "ja_16.png");
                buttonListAbos.add(jButtonAbosEinschalten);
                jButtonAbosAusschalten = new MVButton(new String[]{TOOLBAR_TAB_ABOS}, "Abos ausschalten", "Abos ausschalten", "nein_32.png", "nein_16.png");
                buttonListAbos.add(jButtonAbosAusschalten);
                jButtonAbosLoeschen = new MVButton(new String[]{TOOLBAR_TAB_ABOS}, "Abos löschen", "Abos löschen", "del_32.png", "del_16.png");
                buttonListAbos.add(jButtonAbosLoeschen);
                jButtonAboAendern = new MVButton(new String[]{TOOLBAR_TAB_ABOS}, "Abo ändern", "Abo ändern", "configure_32.png", "configure_16.png");
                buttonListAbos.add(jButtonAboAendern);
                break;
        }
        switch (state) {
            case TOOLBAR_TAB_FILME:
                this.add(filler__5);
                this.add(jButtonFilmlisteLaden);
                this.add(filler__10);
                this.add(jButtonInfo);
                this.add(filler__10);
                this.add(jButtonFilmAbspielen);
                this.add(jButtonFilmSpeichern);
                this.add(filler__10);
                this.add(filler__trenner);

                // Searchfield
                jButtonFilterPanel = new JButton();
                jTextFieldFilter = new org.jdesktop.swingx.JXSearchField();
                jTextFieldFilter.setBackground(new java.awt.Color(230, 230, 230));
                jTextFieldFilter.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                jTextFieldFilter.setToolTipText("Thema/Titel suchen");
                jTextFieldFilter.setDisabledTextColor(new java.awt.Color(102, 102, 102));
                jTextFieldFilter.setMaximumSize(new java.awt.Dimension(300, 35));
                jTextFieldFilter.setName("Thema/Titel");
                jTextFieldFilter.setPreferredSize(new java.awt.Dimension(300, 25));
                jTextFieldFilter.setPrompt("Thema/Titel");
                jTextFieldFilter.setLayoutStyle(JXSearchField.LayoutStyle.MAC);
                jTextFieldFilter.setSearchMode(JXSearchField.SearchMode.INSTANT);
                jTextFieldFilter.setUseNativeSearchFieldIfPossible(true);
                jTextFieldFilter.getFindButton().setIcon(GetIcon.getProgramIcon("suchen_22.png"));
                jTextFieldFilter.addActionListener(actionEvent -> {
                    Filter.checkPattern2(jTextFieldFilter);
                    Daten.guiFilme.guiFilmeFiltern();
                });
                //looks like you need to explicitly set this on Linux...
                jTextFieldFilter.setInstantSearchDelay(150);
                this.add(jTextFieldFilter);

                // Button Filter
                jButtonFilterPanel.setToolTipText("Erweiterte Suche (Filter) anzeigen/ausblenden");
                jButtonFilterPanel.setBorder(null);
                jButtonFilterPanel.setBorderPainted(false);
                jButtonFilterPanel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButtonFilterPanel.setMaximumSize(new java.awt.Dimension(40, 40));
                jButtonFilterPanel.setMinimumSize(new java.awt.Dimension(40, 40));
                jButtonFilterPanel.setOpaque(false);
                jButtonFilterPanel.setPreferredSize(new java.awt.Dimension(40, 40));
                jButtonFilterPanel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                jButtonFilterPanel.setIcon(GetIcon.getProgramIcon("filter_anzeigen_22.png"));
                this.add(jButtonFilterPanel);
                break;
            case TOOLBAR_TAB_DOWNLOADS:
                this.add(filler__10);
                this.add(jButtonInfo);
                this.add(filler__10);
                this.add(jButtonDownloadAktualisieren);
                this.add(jButtonDownloadAlleStarten);
                this.add(jButtonDownloadFilmStarten);
                this.add(jButtonDownloadZurueckstellen);
                this.add(jButtonDownloadLoeschen);
                this.add(jButtonDownloadAufraeumen);
                break;
            case TOOLBAR_TAB_ABOS:
                this.add(filler__10);
                this.add(jButtonAbosEinschalten);
                this.add(jButtonAbosAusschalten);
                this.add(jButtonAbosLoeschen);
                this.add(jButtonAboAendern);
                break;
        }

        this.add(filler__10);
        // Icons
        if (!nrIconKlein.isEmpty()) {
            setIcon(Boolean.parseBoolean(MVConfig.get(nrIconKlein)));
        }
        loadVisible();
        initListener();
    }

    public final void setIcon(boolean klein) {
        MVConfig.add(nrIconKlein, Boolean.toString(klein));
        beobMausToolBar.itemKlein.setSelected(klein);
        if (jButtonFilmlisteLaden != null) {
            jButtonFilmlisteLaden.setIcon();
        }
        if (jButtonFilmAbspielen != null) {
            jButtonFilmAbspielen.setIcon();
        }
        if (jButtonFilmSpeichern != null) {
            jButtonFilmSpeichern.setIcon();
        }
        if (jButtonInfo != null) {
            jButtonInfo.setIcon();
        }
        if (jButtonDownloadAktualisieren != null) {
            jButtonDownloadAktualisieren.setIcon();
        }
        if (jButtonDownloadAlleStarten != null) {
            jButtonDownloadAlleStarten.setIcon();
        }
        if (jButtonDownloadFilmStarten != null) {
            jButtonDownloadFilmStarten.setIcon();
        }
        if (jButtonDownloadZurueckstellen != null) {
            jButtonDownloadZurueckstellen.setIcon();
        }
        if (jButtonDownloadLoeschen != null) {
            jButtonDownloadLoeschen.setIcon();
        }
        if (jButtonDownloadAufraeumen != null) {
            jButtonDownloadAufraeumen.setIcon();
        }
        if (jButtonAbosLoeschen != null) {
            jButtonAbosLoeschen.setIcon();
        }
        if (jButtonAbosEinschalten != null) {
            jButtonAbosEinschalten.setIcon();
        }
        if (jButtonAbosAusschalten != null) {
            jButtonAbosAusschalten.setIcon();
        }
        if (jButtonAboAendern != null) {
            jButtonAboAendern.setIcon();
        }
        this.repaint();
    }

    public void setToolbar() {
        if (state != null) {
            setToolbar(state);
        }
    }

    public void setToolbar(String sstate) {
        state = sstate;
        boolean ok;
        filterAnzeigen();
        for (MVButton b : buttonListToUse) {
            ok = false;
            if (b.sparte.contains(sstate)) {
                b.setEnabled(true);
                b.setVisible(b.anzeigen);
                ok = true;
            }
            if (!ok) {
//////                if (!Boolean.parseBoolean(MVConfig.get(MVConfig.SYSTEM_VIS_DOWNLOAD)) && b.sparte.contains(TOOLBAR_TAB_DOWNLOADS)
//////                        || Boolean.parseBoolean(MVConfig.get(MVConfig.SYSTEM_FENSTER_DOWNLOAD)) && b.sparte.contains(TOOLBAR_TAB_DOWNLOADS)) {
//////                    b.setVisible(false);
//////                } else if (!Boolean.parseBoolean(MVConfig.get(MVConfig.SYSTEM_VIS_ABO)) && b.sparte.contains(TOOLBAR_TAB_ABOS)
//////                        || Boolean.parseBoolean(MVConfig.get(MVConfig.SYSTEM_FENSTER_ABO)) && b.sparte.contains(TOOLBAR_TAB_ABOS)) {
//////                    b.setVisible(false);
//////                } else {
//////                    b.setEnabled(false);
//////                }
            }
        }
    }

    public void filterAnzeigen() {
        switch (state) {
            case TOOLBAR_TAB_FILME:
                jButtonFilterPanel.setEnabled(state.equals(TOOLBAR_TAB_FILME));
                jTextFieldFilter.setEnabled(state.equals(TOOLBAR_TAB_FILME));
                jTextFieldFilter.setVisible(!Boolean.parseBoolean(MVConfig.get(MVConfig.SYSTEM_VIS_FILTER)));
                break;
            case TOOLBAR_TAB_DOWNLOADS:
                break;
            case TOOLBAR_TAB_ABOS:
                break;
        }
    }

    public void loadVisible() {
        if (!nrToolbar.isEmpty()) {
            String[] b = MVConfig.get(nrToolbar).split(":");
            if (buttonListToUse.size() == b.length) {
                // ansonsten gibt es neue Button: dann alle anzeigen
                for (int i = 0; i < b.length; ++i) {
                    buttonListToUse.get(i).anzeigen = Boolean.parseBoolean(b[i]);
                    buttonListToUse.get(i).setVisible(Boolean.parseBoolean(b[i]));
                }
            }
        }
        setToolbar();
        if (!nrIconKlein.isEmpty()) {
            setIcon(Boolean.parseBoolean(MVConfig.get(nrIconKlein)));
        }
    }

    private void storeVisible() {
        if (!nrToolbar.isEmpty()) {
            MVConfig.add(nrToolbar, "");
            for (MVButton b : buttonListToUse) {
                if (!MVConfig.get(nrToolbar).isEmpty()) {
                    MVConfig.add(nrToolbar, MVConfig.get(nrToolbar) + ":");
                }
                MVConfig.add(nrToolbar, MVConfig.get(nrToolbar) + Boolean.toString(b.anzeigen));

            }
        }
    }

    private void initListener() {
        Listener.addListener(new Listener(Listener.EREIGNIS_PANEL_FILTER_ANZEIGEN, ToolBar.class.getSimpleName()) {
            @Override
            public void ping() {
                filterAnzeigen();
            }
        });
        addMouseListener(beobMausToolBar);

        switch (state) {
            case TOOLBAR_TAB_FILME:
                Daten.filmeLaden.addAdListener(new ListenerFilmeLaden() {
                    @Override
                    public void start(ListenerFilmeLadenEvent event) {
                        //ddaten.infoPanel.setProgress();
                        jButtonFilmlisteLaden.setEnabled(false);
                    }

                    @Override
                    public void progress(ListenerFilmeLadenEvent event) {
                    }

                    @Override
                    public void fertig(ListenerFilmeLadenEvent event) {
                        jButtonFilmlisteLaden.setEnabled(true);
                    }
                });
                jButtonFilmlisteLaden.addActionListener(e -> Daten.filmeLaden.filmeLaden(daten, false));
                jButtonFilmlisteLaden.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        if (arg0.isPopupTrigger()) {
                            if (jButtonFilmlisteLaden.isEnabled()) {
                                Daten.filmeLaden.filmeLaden(daten, true);
                            }
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                        if (arg0.isPopupTrigger()) {
                            if (jButtonFilmlisteLaden.isEnabled()) {
                                Daten.filmeLaden.filmeLaden(daten, true);
                            }
                        }
                    }
                });
                jButtonFilmSpeichern.addActionListener(e -> Daten.guiFilme.guiFilmeFilmSpeichern());
                jButtonFilmAbspielen.addActionListener(e -> Daten.guiFilme.guiFilmeFilmAbspielen());
                jButtonInfo.addActionListener(e -> daten.filmInfo.showInfo());
                jButtonFilterPanel.addActionListener(e -> {
                    boolean b = !Boolean.parseBoolean(MVConfig.get(MVConfig.SYSTEM_VIS_FILTER));
                    MVConfig.add(MVConfig.SYSTEM_VIS_FILTER, Boolean.toString(b));
                    filterAnzeigen();
                    Listener
                            .notify(Listener.EREIGNIS_PANEL_FILTER_ANZEIGEN, ToolBar.class
                                    .getName());
                });
                break;
            case TOOLBAR_TAB_DOWNLOADS:
                jButtonInfo.addActionListener(e -> daten.filmInfo.showInfo());
                jButtonDownloadAktualisieren.addActionListener(e -> Daten.guiDownloads.aktualisieren());
                jButtonDownloadAufraeumen.addActionListener(e -> Daten.guiDownloads.aufraeumen());
                jButtonDownloadLoeschen.addActionListener(e -> Daten.guiDownloads.loeschen());
                jButtonDownloadAlleStarten.addActionListener(e -> Daten.guiDownloads.starten(true));
                jButtonDownloadFilmStarten.addActionListener(e -> Daten.guiDownloads.filmAbspielen());
                jButtonDownloadZurueckstellen.addActionListener(e -> Daten.guiDownloads.zurueckstellen());
                break;
            case TOOLBAR_TAB_ABOS:
                jButtonAbosEinschalten.addActionListener(e -> Daten.guiAbo.einAus(true));
                jButtonAbosAusschalten.addActionListener(e -> Daten.guiAbo.einAus(false));
                jButtonAbosLoeschen.addActionListener(e -> Daten.guiAbo.loeschen());
                jButtonAboAendern.addActionListener(actionEvent -> Daten.guiAbo.aendern());
                break;

        }

        // Tab Filme
        // Tab Downloads
        // Tab Abo
    }

    private class MVButton extends JButton {

        boolean anzeigen = true;
        String name = "";
        String imageIconKlein;
        String imageIconNormal;
        ArrayList<String> sparte = new ArrayList<>();

        public MVButton(String[] ssparte, String nname, String ttoolTip,
                String iimageIconNormal, String iimageIconKlein) {
            setToolTipText(ttoolTip);
            name = nname;
            imageIconKlein = iimageIconKlein;
            imageIconNormal = iimageIconNormal;
            sparte.addAll(Arrays.asList(ssparte));
            setOpaque(false);
            setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
            setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        }

        void setIcon() {
            if (!nrIconKlein.isEmpty()) {
                if (Boolean.parseBoolean(MVConfig.get(nrIconKlein))) {
                    this.setIcon(GetIcon.getProgramIcon(imageIconKlein));
                } else {
                    this.setIcon(GetIcon.getProgramIcon(imageIconNormal));
                }
            }
        }
    }

    private class BeobMausToolBar extends MouseAdapter {

        JCheckBoxMenuItem itemKlein = new JCheckBoxMenuItem("kleine Icons");
        JMenuItem itemReset = new JMenuItem("zurücksetzen");
        JCheckBoxMenuItem[] box;

        public BeobMausToolBar() {
            if (!nrIconKlein.isEmpty()) {
                itemKlein.setSelected(Boolean.parseBoolean(MVConfig.get(nrIconKlein)));
            }
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            if (arg0.isPopupTrigger()) {
                showMenu(arg0);
            }
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            if (arg0.isPopupTrigger()) {
                showMenu(arg0);
            }
        }

        private void showMenu(MouseEvent evt) {
            JPopupMenu jPopupMenu = new JPopupMenu();
            itemKlein.addActionListener(e -> setIcon(itemKlein.isSelected()));
            jPopupMenu.add(itemKlein);
            //##Trenner##
            jPopupMenu.addSeparator();
            //##Trenner##

            // Spalten ein-ausschalten
            box = new JCheckBoxMenuItem[buttonListToUse.size()];
            for (int i = 0; i < box.length; ++i) {
                box[i] = null;
                if (extern) {
                    for (String s : buttonListToUse.get(i).sparte) {
                        if (s.equals(state)) {
                            box[i] = new JCheckBoxMenuItem(buttonListToUse.get(i).name);
                            break;
                        }
                    }
                } else {
                    box[i] = new JCheckBoxMenuItem(buttonListToUse.get(i).name);
                }
                if (box[i] != null) {
                    box[i] = new JCheckBoxMenuItem(buttonListToUse.get(i).name);
                    box[i].setIcon(GetIcon.getProgramIcon(buttonListToUse.get(i).imageIconKlein));
                    box[i].setSelected(buttonListToUse.get(i).anzeigen);
                    box[i].addActionListener(e -> {
                        setButtonList();
                        storeVisible();
                    });
                    jPopupMenu.add(box[i]);
                }
            }
            //##Trenner##
            jPopupMenu.addSeparator();
            //##Trenner##
            itemReset.addActionListener(e -> {
                resetToolbar();
                storeVisible();
            });
            jPopupMenu.add(itemReset);

            //anzeigen
            jPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }

        private void setButtonList() {
            if (box == null) {
                return;
            }
            for (int i = 0; i < box.length; ++i) {
                if (box[i] == null) {
                    continue;
                }
                buttonListToUse.get(i).anzeigen = box[i].isSelected();
                buttonListToUse.get(i).setVisible(box[i].isSelected());
            }
            setToolbar();
        }

        private void resetToolbar() {
            if (box == null) {
                return;
            }
            for (int i = 0; i < box.length; ++i) {
                if (box[i] == null) {
                    continue;
                }
                buttonListToUse.get(i).anzeigen = true;
                buttonListToUse.get(i).setVisible(true);
            }
            setToolbar();
            setIcon(false);
        }

    }
}